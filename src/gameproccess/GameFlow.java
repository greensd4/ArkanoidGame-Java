package gameproccess;

import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import info.Counter;
import info.HighScoresTable;
import info.ScoreInfo;
import levels.LevelInformation;
import screens.EndScreen;
import screens.HighScoresAnimation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * GameFlow.
 *
 * @author Daniel Greenspan.
 */
public class GameFlow {

    private GUI screen;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private HighScoresTable highScoresTable;
    private File highScoreFile;

    /**
     * constructor.
     *
     * @param screen          - the gui screen.
     * @param ar              - the animation runner.
     * @param highScoresTable - the high score table.
     * @param file - the high score table file.
     */
    public GameFlow(GUI screen, AnimationRunner ar, HighScoresTable highScoresTable, File file) {
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.lives = new Counter(7);
        this.screen = screen;
        this.keyboardSensor = screen.getKeyboardSensor();
        this.highScoresTable = highScoresTable;
        highScoreFile = file;
    }

    /**
     * runLevels.
     * runs threw the games levels and stops only when run out of lives.
     *
     * @param levels - a list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(screen, levelInfo, this.score, this.lives,
                    this.keyboardSensor, this.animationRunner);

            level.initialize();

            while (level.getNumOfBlocks() > 0 && this.lives.getValue() > 0) {
                level.playOneTurn();
            }

            if (lives.getValue() == 0) {
                break;
            }
        }
        //end screen.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY,
                new EndScreen(winOrLose(), this.score.getValue())));
        //adds the high score (if indeed its an high score) to the high scores table.
        if (highScoresTable.getRank(this.score.getValue()) != 0) {

            DialogManager dialog = screen.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            highScoresTable.getHighScores().remove(highScoresTable.getHighScores().size() - 1);

            highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
            try {
                highScoresTable.save(highScoreFile);
            } catch (IOException ex) {
                System.out.println("IOException is caught");
            }
            //high score table animation.
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new HighScoresAnimation(highScoresTable)));
        }
    }

    /**
     * winOrLose.
     *
     * @return false if the player lost and true otherwise.
     */
    public boolean winOrLose() {
        if (this.lives.getValue() == 0) {
            return false;
        }
        return true;
    }
}