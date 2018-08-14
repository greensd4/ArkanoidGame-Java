package animation;



import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * AnimationRunner.
 * @author Daniel Greenspan.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;
    private double dt;

    /**
     * constructor.
     * @param g - the game.
     * @param frames - number of frames per mill sec.
     */
    public AnimationRunner(GUI g, int frames) {
        this.gui = g;
        this.framesPerSecond = frames;
        this.sleeper = new biuoop.Sleeper();
        this.dt = (double) 1 / frames;
    }

    /**
     * run.
     * the loop of the animation.
     * @param animation - the animation to be looped threw.
     */

    public void run(Animation animation) {
        int millisecondsPerFrame = this.framesPerSecond;
        //double dt = ((double) 1 / (double) millisecondsPerFrame);
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, dt);

            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}

