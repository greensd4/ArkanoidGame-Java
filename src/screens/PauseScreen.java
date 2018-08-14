package screens;

import biuoop.DrawSurface;
import animation.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

/**
 * PauseScreen.
 *
 * @author Daniel Greenspan.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * doOneFrame.
     *
     * @param d - the draw surface.
     * @param dt  - the amount.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Image img = null;
        String pauseBackground = "background_images/PauseYourGame.jpg";
        try {
            img = ImageIO.read(ClassLoader.getSystemResourceAsStream(pauseBackground));
        } catch (IOException e) {
            System.out.println("no such image in directory");
        }
        d.drawImage(0, 0, img);
        d.setColor(Color.white);
        d.drawText(20, d.getHeight() - 40, "press space to continue", 20);
    }

    /**
     * shouldStop.
     *
     * @return true when space key is pressed false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
