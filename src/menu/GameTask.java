package menu;

import animation.AnimationRunner;
import biuoop.GUI;
import gameproccess.GameFlow;
import info.HighScoresTable;
import levels.LevelInformation;

import java.io.File;
import java.util.List;

/**
 * GameTask.
 *
 * @author Daniel Greenspan.
 */
public class GameTask implements Task<Void> {
    private GUI screen;
    private HighScoresTable highScoresTable;
    private AnimationRunner runner;
    private List<LevelInformation> levels;
    private File highScoresFile;

    /**
     * constructor.
     * @param runner - the animation runner.
     * @param highScores - the game animation.
     * @param screen - the game's screen.
     * @param gameLevels - list of the game's levels.
     * @param file - the high scores file.
     */
    public GameTask(GUI screen, AnimationRunner runner, HighScoresTable highScores,
                    File file, List<LevelInformation> gameLevels) {
        this.runner = runner;
        this.levels = gameLevels;
        this.screen = screen;
        this.highScoresFile = file;
        this.highScoresTable = highScores;
    }

    /**
     * runs the task.
     * @return - a value.
     */
    public Void run() {
        GameFlow game = new GameFlow(screen, runner, highScoresTable, highScoresFile);
        game.runLevels(this.levels);
        return null;
    }
}
