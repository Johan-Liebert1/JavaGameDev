package graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 100, height = 175;

    public static BufferedImage mario1, mario2, mario3, mario4, mario5;

    public static void init() {
        // only going to be called once
        ImageLoader il = new ImageLoader();
        SpriteSheet sheet = new SpriteSheet(il.loadImage("../assets/images/mario.png"));

        mario1 = sheet.crop(0, 0, width, height);
        mario1 = sheet.crop(width, 0, width, height);
        mario1 = sheet.crop(width * 2, 0, width, height);
        mario1 = sheet.crop(width * 3, 0, width, height);
        mario1 = sheet.crop(width * 4, 0, width, height);

    }
}
