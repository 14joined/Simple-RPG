package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.DarkGate;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.ui.UIImage;
import dev.codenmore.tilegame.ui.UIManager;
import dev.codenmore.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    // UI
    private UIManager uiManager;

    private World world;

    public GameState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);

    }

    @Override
    public void tick() {
        world.tick();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        UIImage img = new UIImage(64, 64, 32, 32,
                Assets.bullets[handler.getWorld().getEntityManager().getPlayer().getHealth()]);
        uiManager.addObject(img);
        uiManager.render(g);
        uiManager.removeObject(img);
    }
}
