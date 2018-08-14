package screens;

import biuoop.DrawSurface;
import animation.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

/**
 * EndScreen.
 *
 * @author Daniel Greenspan.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private boolean winOrLose;
    private int score;

    /**
     * constructor.
     *
     * @param winOrLose - true if all the levels were cleared, false otherwise.
     * @param score     - the game's score.
     */
    public EndScreen(boolean winOrLose, int score) {
        this.stop = false;
        this.winOrLose = winOrLose;
        this.score = score;
    }

    /**
     * doOneFrame.
     *
     * @param d - the draw surface.
     * @param dt - the amount.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Image img = null;
        if (!this.winOrLose) {
            String loseBackground = "background_images/lose.jpg";
            try {
                img = ImageIO.read(ClassLoader.getSystemResourceAsStream(loseBackground));
            } catch (IOException e) {
                System.out.println("no such image in directory");
            }
            d.drawImage(0, 0, img);
            d.setColor(new Color(0xD212A6));
            d.drawText(50, 400, "Game Over. Your score is: " + score, 45);
        } else {
            String winBackground = "background_images/win.jpg";
            try {
                img = ImageIO.read(ClassLoader.getSystemResourceAsStream(winBackground));
            } catch (IOException e) {
                System.out.println("no such image in directory");
            }
            d.drawImage(0, 0, img);
            d.setColor(new Color(0x10D91A));
            d.drawText(50, d.getHeight() / 2, "You Win! Your score is: " + score, 45);
        }
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
