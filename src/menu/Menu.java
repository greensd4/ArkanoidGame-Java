package menu;

import animation.Animation;

/**
 * Menu.
 * @param <T> - the menus type.
 * @author Daniel Greenspan.
 */
public interface Menu<T> extends Animation {

    /**
     * addSelection.
     * @param key - key to wait for.
     * @param message -  line to print.
     * @param returnVal -  what to return.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * getStatus.
     * @return - the menu's status.
     */
    T getStatus();

    /**
     * addSubMenu.
     * @param key - key to wait for.
     * @param message - line to print.
     * @param subMenu a new sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}