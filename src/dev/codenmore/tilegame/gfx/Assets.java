package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {

    private static final int width = 16, height = 16;

    public static BufferedImage dirt, grass, stone, tree, water, darkGate, sand;
    public static BufferedImage coin, head;
    public static BufferedImage[] bullets;
    public static BufferedImage[] player_down, player_up, player_left, player_right, standing;
    public static BufferedImage[] enemy_down, enemy_left;
    public static BufferedImage[] pre_enemy_down, pre_enemy_left;
    public static BufferedImage[] buttonStart;
    public static BufferedImage[] twoPlayers;
    public static BufferedImage endGame;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet stateSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
        // SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/itemSheet.png"));

        endGame = stateSheet.crop(0, 0, 75 * width, 39 * height);

        twoPlayers = new BufferedImage[2];
        twoPlayers[0] = sheet.crop(width,5 * height, width, height);
        twoPlayers[1] = sheet.crop(width,9 * height, width, height);

        bullets = new BufferedImage[12];
        bullets[0] = sheet.crop(6 * width, 2 * height, width, height);
        bullets[1] = sheet.crop(6 * width, 1 * height, width, height);
        bullets[2] = sheet.crop(7 * width, 0, width, height);

        bullets[3] = sheet.crop(6 * width, 2 * height, width, height);
        bullets[4] = sheet.crop(6 * width, 1 * height, width, height);
        bullets[5] = sheet.crop(7 * width, 0, width, height);
        bullets[6] = sheet.crop(6 * width, 2 * height, width, height);
        bullets[7] = sheet.crop(6 * width, 1 * height, width, height);
        bullets[8] = sheet.crop(7 * width, 0, width, height);
        bullets[9] = sheet.crop(6 * width, 2 * height, width, height);
        bullets[10] = sheet.crop(6 * width, 1 * height, width, height);

        coin = sheet.crop(9 * width, height, width, height);
        head = sheet.crop(8 * width, 0, width, height);

        enemy_down = new BufferedImage[2];
        enemy_left = new BufferedImage[2];

        enemy_down[1] = sheet.crop(10 * width, 6 * height, 2 * width, 2 * height);
        enemy_down[0] = sheet.crop(12 * width, 6 * height, 2 * width, 2 * height);
        enemy_left[0] = sheet.crop(10 * width - 1, 12 * height, 2 * width, 2 * height);
        enemy_left[1] = sheet.crop(12 * width - 1, 12 * height, 2 * width, 2 * height);

        pre_enemy_down = new BufferedImage[2];
        pre_enemy_left = new BufferedImage[2];
        pre_enemy_down[0] = sheet.crop(10 * width, 2 * height, 2 * width, 2 * height);
        pre_enemy_down[1] = sheet.crop(12 * width, 2 * height, 2 * width, 2 * height);
        pre_enemy_left[0] = sheet.crop(12 * width - 1, 2 * height, 2 * width, 2 * height);
        pre_enemy_left[1] = sheet.crop(10 * width - 1, 2 * height, 2 * width, 2 * height);

        buttonStart = new BufferedImage[2];
        buttonStart[0] = sheet.crop(10 * width, 2 * height, 2 * width, 2 * height);
        buttonStart[1] = sheet.crop(12 * width, 2 * height, 2 * width, 2 * height);

        player_down = new BufferedImage[3];
        player_up = new BufferedImage[3];
        player_left = new BufferedImage[3];
        player_right = new BufferedImage[3];
        standing = new BufferedImage[1];

        player_down[0] = sheet.crop(0, height * 2, width, height);
        player_down[1] = sheet.crop(width, height * 2, width, height);
        player_down[2] = sheet.crop(width * 2, height * 2, width, height);
        player_up[0] = sheet.crop(0, 0, width, height);
        player_up[1] = sheet.crop(width, 0, width, height);
        player_up[2] = sheet.crop(width * 2, 0, width, height);
        player_left[0] = sheet.crop(0, height * 3, width, height);
        player_left[1] = sheet.crop(width, height * 3, width, height);
        player_left[2] = sheet.crop(width * 2, height * 3, width, height);
        player_right[0] = sheet.crop(0, height, width, height);
        player_right[1] = sheet.crop(width, height, width, height);
        player_right[2] = sheet.crop(width * 2, height, width, height);
        standing[0] = sheet.crop(0, height * 2, width, height);

        dirt = sheet.crop(width * 12, height * 15, width, height);
        grass = sheet.crop(width, height * 15, width, height);
        stone = sheet.crop(width * 7, height * 15, width, height);
        tree = sheet.crop(width * 6, height * 15, width, height);
        water = sheet.crop(width * 4,height * 15, width, height);
        darkGate = sheet.crop(width * 2, height * 15, width, height);
        sand = sheet.crop(0, 15 * height, width, height);
    }

}
