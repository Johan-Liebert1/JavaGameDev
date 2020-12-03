package graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {

    public BufferedImage loadImage(String path) {
        try {
            System.out.print(path);
            return ImageIO.read(this.getClass().getResource(path));

        } catch (Exception e) {
            e.printStackTrace();
            // System.exit(1);
        }

        return null;

    }

}
