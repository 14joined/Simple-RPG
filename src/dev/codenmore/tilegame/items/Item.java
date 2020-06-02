package dev.codenmore.tilegame.items;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Item {

    // Handler

    public static Item[] items = new Item[256];
    public static Item coinItem = new Item(Assets.coin, "Coin", 0);
    public static Item headItem = new Item(Assets.head, "Head", 1);

    // Classes

    public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32, PICKED_UP = -1;

    protected Handler handler;
    protected BufferedImage texture;
    protected  String name;
    protected final int id;

    protected int x, y, count;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        items[id] = this;
    }

    public void tick() {}

    public void render(Graphics g) {
        if (handler == null)
            return;
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }

    public Handler getHandler() {
        return handler;
    }

    public Item createNew(int x, int y) {
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and setters

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
