package dev.codenmore.tilegame.states;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImage;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImage(64, 128, 128, 128, Assets.grass));

        uiManager.addObject(new UIImageButton(32, 30, 190, 190, Assets.buttonStart, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(64, 220, 128 , 128, Assets.enemy_down, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        // uiManager.addObject(new UIImageButton(64, 400, 128, 128, Assets.twoPlayers, new ClickListener() {
        //    @Override
        //    public void onClick() {
        //        handler.getMouseManager().setUiManager(null);
        //        State.setState(handler.getGame().gameState);
        //    }
        //}));
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
