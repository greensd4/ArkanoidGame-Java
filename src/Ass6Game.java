import animation.MenuAnimation;
import biuoop.GUI;
import animation.AnimationRunner;
import info.HighScoresTable;
import levels.LevelSetInfo;
import levels.LevelSpecificationReader;
import levels.LevelInformation;
import levels.ReadLevelSets;
import menu.GameTask;
import menu.QuitTask;
import menu.ShowHiScoresTask;
import menu.Task;
import menu.Menu;
import screens.HighScoresAnimation;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Ass6Game.
 *
 * @author Daniel Greenspan.
 */

public class Ass6Game {
    /**
     * main.
     * The Arkanoid game.
     *
     * @param args - args none.
     */
    public static void main(String[] args) {
        GUI screen = new GUI("Arkanoid", 800, 600);
        File file = new File("highscores");

        //gets levels from args/ if no args gets levels in a specific way.
        AnimationRunner ar = new AnimationRunner(screen, 60);
        String levelSetPath = "level_sets.txt";
        if (args.length != 0) {
            levelSetPath = args[0];
        }
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetPath);
        HighScoresTable highScoresTable = null;

        List<LevelSetInfo> sets = ReadLevelSets.fromReader(new InputStreamReader(is));

        //open the high score file.
        if (file.exists()) {
            highScoresTable = HighScoresTable.loadFromFile(file);
        } else {
            try {
                highScoresTable = new HighScoresTable(10);
                highScoresTable.save(file);
            } catch (Exception e) {
                System.out.println("IOException is caught");
            }
        }
        while (true) {
            Menu<Task<Void>> subMenu = subMenuCreator(screen, ar, sets, highScoresTable, file);
            //menu initialize.
            Menu<Task<Void>> menu = new MenuAnimation<>("Arkanoid", screen.getKeyboardSensor(), ar);
            //adds sub menus and selections to main menu.
            menu.addSubMenu("s", "Start new game", subMenu);
            menu.addSelection("h", "High scores",
                    new ShowHiScoresTask(ar, new HighScoresAnimation(highScoresTable), screen.getKeyboardSensor()));
            menu.addSelection("q", "Quit", new QuitTask());

            //menu run.
            //while (true) {
            ar.run(menu);
            Task<Void> t = menu.getStatus();
            t.run();
        }
    }

    /**
     * subMenuCreator.
     *
     * @param screen     - the game screen.
     * @param ar         - the animation runner.
     * @param sets       - the list of levelSetInfo.
     * @param highScores - the high scores table.
     * @param file       - the high scores table file.
     * @return - a new sub menu of Menu type.
     */
    public static Menu<Task<Void>> subMenuCreator(GUI screen, AnimationRunner ar, List<LevelSetInfo> sets,
                                                  HighScoresTable highScores, File file) {


        Menu<Task<Void>> subMenu = new MenuAnimation<>("Start new Game", screen.getKeyboardSensor(), ar);
        for (LevelSetInfo l : sets) {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(l.getLevelPath());
            LevelSpecificationReader lsr = new LevelSpecificationReader();
            List<LevelInformation> listOfLevels = lsr.fromReader(new InputStreamReader(is));
            subMenu.addSelection(l.getKey(), l.getMessage(), new GameTask(screen, ar, highScores, file, listOfLevels));
        }
        subMenu.addSelection("b", "back", new Task<Void>() {
            @Override
            public Void run() {
                return null;
            }
        });
        return subMenu;
    }
}

