package dev.codenmore.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, heal, pause, reset, quit;
    public boolean up1, down1, left1, right1;
    public boolean aUp, aDown, aLeft, aRight;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];

        up1 = keys[KeyEvent.VK_I];
        down1 = keys[KeyEvent.VK_K];
        left1 = keys[KeyEvent.VK_J];
        right1 = keys[KeyEvent.VK_L];

        aUp = keys[KeyEvent.VK_UP];
        aDown = keys[KeyEvent.VK_DOWN];
        aLeft = keys[KeyEvent.VK_LEFT];
        aRight = keys[KeyEvent.VK_RIGHT];

        heal = keys[KeyEvent.VK_F];

        pause = keys[KeyEvent.VK_P];
        reset = keys[KeyEvent.VK_R];

        quit = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println("Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
