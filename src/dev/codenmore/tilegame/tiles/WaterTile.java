package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class WaterTile extends Tile {

    public WaterTile(int id) {
        super(Assets.water, id);
    }

    @Override
    public boolean isRiver() {
        return true;
    }
}
