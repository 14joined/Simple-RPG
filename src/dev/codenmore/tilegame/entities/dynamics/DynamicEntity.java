package dev.codenmore.tilegame.entities.dynamics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;

public abstract class DynamicEntity extends Entity {

    public static final float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_BULLET_WIDTH = 32,
            DEFAULT_BULLET_HEIGHT = 32;

    protected float speed;
    protected float xMove, yMove;

    public DynamicEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        moveX();
        moveY();
    }

    public void moveX() {

    }

    public void moveY() {

    }

    public boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

}
