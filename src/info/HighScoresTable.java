package info;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by green on 06/06/2017.
 */
public class HighScoresTable implements Serializable {
    private int sizeOfTable;
    private List<ScoreInfo> scoreTable;
    /**
     * constructor.
     *
     * @param size - the size of table wanted.
     */
    public HighScoresTable(int size) {
        this.sizeOfTable = size;
        this.scoreTable = new ArrayList<>(size);
        ScoreInfo noneScore = new ScoreInfo("NONE", 0);
        for (int i = 0; i < this.sizeOfTable; i++) {
            this.scoreTable.add(noneScore);
        }
    }

    /**
     * constructor.
     *
     * @param scores      - a list of scores.
     * @param sizeOfTable - the actual size of the table.
     */
    public HighScoresTable(List<ScoreInfo> scores, int sizeOfTable) {
        this.scoreTable = scores;
        this.sizeOfTable = sizeOfTable;
    }
    /**
     * add.
     * @param score - the score info.
     */
    public void add(ScoreInfo score) {
            this.getHighScores().add(score);
            sort();
        }

    /**
     * size.
     * @return - the table's size.
     */
    public int size() {
        return this.sizeOfTable;
    }
    /**
     * getHighScores.
     * @return - the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreTable;
    }
    /**
     * getRank.
     * @param score - current score.
     * @return - the rank of the current score: where will it
     * be on the list if added.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.getHighScores().size(); i++) {
            if (score >= this.scoreTable.get(i).getScore()) {
                return i + 1;
            }
        }
        out.println("your score is too low to enter the score table!");
        return 0;
    }
    /**
     * clear.
     * Clears the table.
     */
    public void clear() {
        for (int i = 0; i < this.sizeOfTable; i++) {
            scoreTable.remove(i);
        }
    }
    /**
     * load.
     * @param filename - the high score file.
     * @throws IOException - if not able to load the highscores table.
     */
    public void load(File filename) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            scoreTable = ((List<ScoreInfo>) in.readObject());

            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("File not found");
            c.printStackTrace();
            return;

        }
    }
    /**
     * save.
     * @param filename - the high score file.
     * @throws IOException - if not able to save the highscores table.
     */
    public void save(File filename) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(scoreTable);
            fileOut.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * HighScoresTable.
     * @param filename - the high scores table file.
     * @return - an HighScoreTable with scores from a file.
     */
    public static HighScoresTable loadFromFile(File filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<ScoreInfo> s = (List<ScoreInfo>) in.readObject();
            HighScoresTable highScore = new HighScoresTable(s, 10);
            in.close();
            fileIn.close();
            return highScore;
        } catch (IOException e) {
            e.printStackTrace();
            return new HighScoresTable(0);
        } catch (ClassNotFoundException c) {
            System.out.println("File not found");
            c.printStackTrace();
            return new HighScoresTable(0);
        }
    }

    /**
     * sort.
     * Sorts the table by score.
     */
    public void sort() {
        if (this.sizeOfTable == 0) {
            return;
        }
        ScoreInfo temp;
        for (int i = 0; i < scoreTable.size(); i++) {
            for (int j = 1; j < scoreTable.size() - i; j++) {
                if (scoreTable.get(j - 1).getScore() <= scoreTable.get(j).getScore()) {
                    //swap elements
                    temp = scoreTable.get(j - 1);
                    scoreTable.set(j - 1, scoreTable.get(j));
                    scoreTable.set(j, temp);
                }
            }
        }
    }
}