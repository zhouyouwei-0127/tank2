import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MyTest {
    
    @Test
    public void testLoadImage() {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(MyTest.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
            assert image != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRotateImage() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(MyTest.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
            BufferedImage image1 = rotateImage(image, 90);
            assert image1 != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
