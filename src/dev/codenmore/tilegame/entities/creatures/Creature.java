package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.tiles.Tile;

public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (!checkEntityCollisions(xMove, 0f))
            moveX();
        if (!checkEntityCollisions(0f, yMove))
            moveY();
    }

    public void eMove() {
        if (!checkEntityCollisions(xMove, 0f))
            x += xMove;
        if (!checkEntityCollisions(0f, yMove))
            y += yMove;
    }

    public void moveX() {
        if (xMove > 0) { // Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                if (swim(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                        swim(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                    x += 2.5 * xMove;
                } else if (trapped(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                        trapped(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                    x += 0.5 * xMove;
                } else {
                    x += xMove;
                }
            } else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { // Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                if (swim(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                        swim(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                    x += 2 * xMove;
                } else if (trapped(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                        trapped(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                    x += 0.5 * xMove;
                } else {
                    x += xMove;
                }
            } else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) { // Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                if (swim((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                        swim((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                    y += 2.5 * yMove;
                } else if (trapped((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                        trapped((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                    y += 0.5 * yMove;
                } else {
                    y += yMove;
                }
            } else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0) { // Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                if (swim((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                        swim((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                    y += 2 * yMove;
                } else if (trapped((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                        trapped((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                    y += 0.5 * yMove;
                } else {
                    y += yMove;
                }
            } else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    public void eMoveX() {
        if (xMove > 0) { // Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                    x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { // Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                    x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void eMoveY() {
        if (yMove < 0) { // Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                    y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0) { // Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                    y += yMove;
            } else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    protected boolean swim(int x, int y) {
        return handler.getWorld().getTile(x, y).isRiver();
    }

    protected boolean trapped(int x, int y) {
        return handler.getWorld().getTile(x, y).isSwamp();
    }
    // Getters and Setters

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
