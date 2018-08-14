package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import menu.Menu;
import menu.TaskInfo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation.
 *
 * @param <T> - the return type.
 */
public class MenuAnimation<T> implements Menu<T>  {

    private boolean stop;
    private String title;
    private List<TaskInfo<T>> selections;
    private KeyboardSensor keyboardSensor;
    private T status;
    private List<String> keys;
    private List<String> messages;
    private List<Menu<T>> subMenus;
    private AnimationRunner animationRunner;


    /**
     * MenuAnimation.
     * @param menuTitle - the menu's title.
     * @param ks - the keyboard.
     * @param ar - the animation runner.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor ks, AnimationRunner ar) {
        this.title = menuTitle;
        this.stop = false;
        this.selections = new ArrayList<>();
        this.keyboardSensor = ks;
        this.keys = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.subMenus = new ArrayList<>();
        this.animationRunner = ar;
    }

    /**
     * doOneFrame.
     * in charge of the logic in the animation loop.
     * @param d  - the draw surface.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xE6D95E));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        int xPos = 100;
        int yPos = 300;
        d.drawText(xPos + 100, 150, this.title, 65);
        d.setColor(Color.BLACK);
        d.drawText(xPos + 101, 151, this.title, 65);
        for (int i = 0; i < subMenus.size(); i++) {
            d.setColor(Color.BLUE);
            d.drawText(xPos, yPos, "(" + keys.get(i) + ")", 35);
            d.setColor(Color.BLACK);
            d.drawText(xPos + 1, yPos + 2, "(" + keys.get(i) + ")", 35);
            d.setColor(Color.BLUE);
            d.drawText(xPos + 50, yPos, messages.get(i), 35);
            d.setColor(Color.BLACK);
            d.drawText(xPos + 51, yPos + 2, messages.get(i), 35);
            yPos += 45;

        }
        if (keys.size() == 0) {
            yPos = 300;
        }
        for (int i = 0; i < selections.size(); i++) {
            d.setColor(Color.BLUE);
            d.drawText(xPos, yPos, "(" + selections.get(i).getKey() + ")", 35);
            d.setColor(Color.BLACK);
            d.drawText(xPos + 1, yPos + 2, "(" + selections.get(i).getKey() + ")", 35);
            d.setColor(Color.BLUE);
            d.drawText(xPos + 50, yPos, selections.get(i).getMessage(), 35);
            d.setColor(Color.BLACK);
            d.drawText(xPos + 51, yPos + 2, selections.get(i).getMessage(), 35);
            yPos += 45;

        }

        for (int i = 0; i < selections.size(); i++) {
            if (keyboardSensor.isPressed(selections.get(i).getKey())) {
                this.status = selections.get(i).getTask();
                this.stop = true;
            }
        }
        for (int i = 0; i < subMenus.size(); i++) {
            if (keyboardSensor.isPressed(keys.get(i))) {
                animationRunner.run(subMenus.get(i));
                this.status = subMenus.get(i).getStatus();
                this.stop = true;
            }
        }
    }

    /**
     * shouldStop.
     * provides the stop condition for the animation.
     * @return true if the loop should stop and false otherwise.
     */
    public boolean shouldStop() {
        if (this.stop) {
            stop = false;
            return true;
        }
        return false;
    }

    /**
     * addSelection.
     * @param key       - key to wait for.
     * @param message   -  line to print.
     * @param returnVal -  what to return.
     */
    public void addSelection(String key, String message, T returnVal) {
        selections.add(new TaskInfo(key, message, returnVal));
    }

    /**
     * getStatus.
     * @return - the task to be ran.
     */
    public T getStatus() {
        return this.status;
    }
    /**
     * addSubMenu.
     * @param key     - key to wait for.
     * @param message - line to print.
     * @param subMenu a new sub menu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keys.add(key);
        this.messages.add(message);
        this.subMenus.add(subMenu);
    }
}