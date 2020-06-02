package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.worlds.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Player extends Creature {

    private State menuState;
    private State gameState;
    private World world;

    // Animation
    private Animation aniDown, aniUp, aniLeft, aniRight, standing;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    private long lastHealing, healingCooldown = 5000, healingTimer = healingCooldown;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 16;
        bounds.height = 8;

        // Animation
        aniDown = new Animation(300, Assets.player_down);
        aniUp = new Animation(300, Assets.player_up);
        aniLeft = new Animation(300, Assets.player_left);
        aniRight = new Animation(300, Assets.player_right);
        standing = new Animation(300, Assets.standing);
    }

    @Override
    public void tick() {
        // Animations
        aniDown.tick();
        aniUp.tick();
        aniLeft.tick();
        aniRight.tick();
        standing.tick();
        // Movements
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttack();
        healing();
    }

    public void healing() {
        healingTimer += System.currentTimeMillis() - lastHealing;
        lastHealing = System.currentTimeMillis();
        if (healingTimer < healingCooldown)
            return;

        if (handler.getKeyManager().heal) {
            if (handler.getWorld().getEntityManager().getPlayer().getHealth() < 10)
                handler.getWorld().getEntityManager().getPlayer().setHealth(getHealth() + 1);
        } else {
            return;
        }

        healingTimer = 0;
    }

    public void checkAttack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 10;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if (handler.getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().pause){
            menuState = new MenuState(handler);
            State.setState(menuState);
        } else if (handler.getKeyManager().reset){
            State.setState(new GameState(handler));
        } else if (handler.getKeyManager().quit) {
            System.exit(0);
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die() {
        System.out.println("You lose");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        State.setState(handler.getGame().endState);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up)
            yMove = -speed;
        if (handler.getKeyManager().down)
            yMove = speed;
        if (handler.getKeyManager().left)
            xMove = -speed;
        if (handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return aniLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return aniRight.getCurrentFrame();
        } else if (yMove < 0) {
            return aniUp.getCurrentFrame();
        } else if (yMove > 0) {
            return aniDown.getCurrentFrame();
        } else {
            return standing.getCurrentFrame();
        }
    }



}
