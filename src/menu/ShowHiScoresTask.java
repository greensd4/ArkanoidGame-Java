package menu;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
/**
 * ShowHiScoresTask.
 *
 * @author Daniel Greenspan.
 */
public class ShowHiScoresTask implements Task<Void> {
    private Animation highScoresAnimation;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    /**
     * constructor.
     * @param runner - the animation runner.
     * @param highScoresAnimation - the high score animation.
     * @param ks - the keyboard sensor.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = ks;
    }

    /**
     * run.
     * @return - null
     */
    public Void run() {
        KeyPressStoppableAnimation highscores =
                new KeyPressStoppableAnimation(keyboardSensor, "space", highScoresAnimation);
        this.runner.run(highscores);
        return null;
    }
}