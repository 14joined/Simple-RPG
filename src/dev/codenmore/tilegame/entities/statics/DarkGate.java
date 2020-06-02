package dev.codenmore.tilegame.entities.statics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.items.Item;
import dev.codenmore.tilegame.tiles.Tile;

import java.awt.*;

public class DarkGate extends StaticEntity {

    public DarkGate(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.coinItem.createNew((int) x,(int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.darkGate, (int) (x - handler.getGameCamera().getxOffset()),
                                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}
