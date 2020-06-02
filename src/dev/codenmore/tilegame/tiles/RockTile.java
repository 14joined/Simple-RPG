package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
