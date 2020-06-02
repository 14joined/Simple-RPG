package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.UIImage;
import dev.codenmore.tilegame.ui.UIManager;

import java.awt.*;

public class EndState extends State {

    private UIManager uiManager;

    public EndState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        if (handler.getWorld().getEntityManager().getPlayer().isActive()) {
            uiManager.addObject(new UIImage(0, 0, 1200, 620, Assets.endGame));
        }
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
