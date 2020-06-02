package dev.codenmore.tilegame;

import dev.codenmore.tilegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Title Game!", 1000, 600);
        game.start();
    }

}
