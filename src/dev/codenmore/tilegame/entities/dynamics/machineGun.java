package dev.codenmore.tilegame.entities.dynamics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class machineGun extends DynamicEntity {

    private Animation ani;

    public machineGun(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);

        // Animation
        ani = new Animation(300, Assets.bullets);

    }

    @Override
    public void tick() {
        x += speed;
        y += speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return ani.getCurrentFrame();
        } else if (xMove > 0) {
            return ani.getCurrentFrame();
        } else if (yMove < 0) {
            return ani.getCurrentFrame();
        } else if (yMove > 0) {
            return ani.getCurrentFrame();
        } else {
            return ani.getCurrentFrame();
        }
    }

}
