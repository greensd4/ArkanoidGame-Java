package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * KeyPressStoppableAnimation.
 * @author Daniel Greenspan.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String keyToStop;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param key - the key who stops the animation.
     * @param sensor - the keyboard sensor.
     * @param animation - the animation that is running.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.keyToStop = key;
        this.animation = animation;
        this.isAlreadyPressed = true;

    }

    /**
     * doOneFrame.
     * in charge of the logic in the animation loop.
     * @param d - the draw surface.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (shouldStop() && isAlreadyPressed) {
          return;
        }
        this.animation.doOneFrame(d, dt);
        isAlreadyPressed = false;
    }

    /**
     * shouldStop.
     * provides the stop condition for the animation.
     * @return true if the loop should stop and false otherwise.
     */
    public boolean shouldStop() {
        if (this.keyboard.isPressed(keyToStop)) {
            return true;
        }
        return false;
    }
}

