package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

public class Enemy extends Creature {

    private Animation aniDown, aniLeft;
    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;

    public Enemy(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        bounds.x = 32;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 16;

        // Animation
        aniDown = new Animation(300, Assets.enemy_down);
        aniLeft = new Animation(300, Assets.enemy_left);

    }

    @Override
    public void tick() {
        // Animations
        aniDown.tick();
        aniLeft.tick();
        findPath();
        // getInput();
        eMove();
        eCheckAttack();
    }

    public void eCheckAttack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(1, 1);
        Rectangle ar = new Rectangle();
        int arSize = 50;
        ar.width = arSize;
        ar.height = arSize;

        ar.x = cb.x + cb.width / 2 + arSize / 2;
        ar.y = cb.y + cb.height / 2 - arSize / 2;

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(32, 16).intersects(ar)) {
                e.hurt(1);
            }
        }

        ar.x = cb.x + cb.width / 2 - arSize / 2;
        ar.y = cb.y + cb.height / 2 - arSize / 2;

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(32, 16).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }
    }

    private void findPath() {
        // Find path to player
        xMove = 0;
        yMove = 0;
        int xPlayer = (int) handler.getWorld().getEntityManager().getPlayer().getX();
        int yPlayer = (int) handler.getWorld().getEntityManager().getPlayer().getY();
        int xMe = (int) x;
        int yMe = (int) y;

        int calcX = xMe - xPlayer;
        int calcY = yMe - yPlayer;
        if (calcX >= 0 && calcY >= 0) {
            xMove -= 0.5 * speed;
            yMove -= 0.5 * speed;
        } else if (calcX >= 0 && calcY < 0) {
            xMove -= 0.5 * speed;
            yMove = (float) (0.5 * speed);
        } else if (calcX < 0 && calcY >= 0) {
            xMove += 0.5 * speed;
            yMove -= 0.5 * speed;
        } else {
            xMove += 0.5 * speed;
            yMove += 0.5 * speed;
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up1)
            yMove = -speed;
        if (handler.getKeyManager().down1)
            yMove = speed;
        if (handler.getKeyManager().left1)
            xMove = -speed;
        if (handler.getKeyManager().right1)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.headItem.createNew((int)x, (int)y));

        System.out.println("You win!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        State.setState(handler.getGame().endState);

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return aniLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return aniDown.getCurrentFrame();
        } else if (yMove < 0) {
            return aniDown.getCurrentFrame();
        } else if (yMove > 0) {
            return aniDown.getCurrentFrame();
        } else {
            return aniDown.getCurrentFrame();
        }
    }
}
