package gameproccess;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;
import geometry.Point;
import indicators.LevelIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import info.Counter;
import info.Velocity;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import objects.Ball;
import objects.Block;
import objects.Collidable;
import objects.Paddle;
import objects.Sprite;
import objects.SpriteCollection;


import screens.CountdownAnimation;
import screens.PauseScreen;

import java.awt.Color;
import java.util.List;

/**
 * GameLevel.
 *
 * @author Daniel Greenspan.
 */
public class GameLevel implements Animation {
    //members.
    private LevelInformation level;
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI screen;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private Counter numOfLives;
    private KeyboardSensor keyboardSensor;

    /**
     * contractor.
     *
     * @param levelInformation - the level's information.
     * @param score            - the score counter.
     * @param lives            - the lives counter.
     * @param screen           - the game's screen.
     * @param ks               - the game's keyboard.
     * @param ar               - the game's animation runner.
     */
    public GameLevel(GUI screen, LevelInformation levelInformation, Counter score, Counter lives,
                     KeyboardSensor ks, AnimationRunner ar) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.numOfBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.numOfBalls = new Counter(levelInformation.numberOfBalls());
        this.score = score;
        this.numOfLives = lives;
        this.runner = ar;
        this.screen = screen;
        this.keyboardSensor = ks;
        this.level = levelInformation;
        addSprite(level.getBackground());

    }

    /**
     * addCollidable.
     *
     * @param c - the collidable to be added to game.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite.
     *
     * @param s - the sprite to be added to game.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removeCollidable.
     * Removes a collidable from game's collidable list.
     *
     * @param c - a Collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite.
     * Removes a Sprite from game's sprite list.
     *
     * @param s - a Sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * removeCollidble.
     * Removes a Collidable from game's sprite list.
     *
     * @param c - a Sprite to be removed.
     */
    public void removeCollidble(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * initialize.
     * Initialize a new game: creates the boundaries and the blocks.
     */
    public void initialize() {
        HitListener ballR = new BallRemover(this, numOfBalls);
        this.sprites.addSprite(new ScoreIndicator(this));
        this.sprites.addSprite(new LivesIndicator(this));
        this.sprites.addSprite(new LevelIndicator(this));
        Block[] boundaries = setBlocksBoundaries();
        List<Block> gameBlocks = level.blocks();
        Block down = new Block(new Point(0, getHeightOfScreen() + 5), getWidthOfScreen(),
                20, Color.white, ballR);
        down.setNumOfHits(10);
        down.addToGame(this);
        for (Block b : boundaries) {
            b.addToGame(this);
        }
        for (Block b : gameBlocks) {
            b.addToGame(this);
            b.addHitListener(new BlockRemover(this, numOfBlocks));
            b.addHitListener(new ScoreTrackingListener(score));
        }
    }

    /**
     * shouldStop.
     *
     * @return false if the program should run and true if the program should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneFrame.
     * in charge of the logic in the animation loop.
     *
     * @param d - the draw surface.
     * @param dt  - the amount.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed(dt);
        // pause screen.
        if (getKeyboard().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    keyboardSensor.SPACE_KEY, new PauseScreen()));
            this.runner.run(new CountdownAnimation(2, 3, sprites));
        }
        // when no more blocks.
        if (this.getNumOfBlocks() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        //when no more balls.
        if (getNumOfBalls() == 0) {
            numOfLives.decrease(1);
            this.running = false;
        }
    }

    /**
     * playOneTurn.
     * plays one turn.
     */
    public void playOneTurn() {
        this.running = true;
        Paddle p = paddleCreator();
        this.ballsCreator();
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        while (!this.shouldStop()) {
            this.runner.run(this);
            numOfBalls.increase(level.numberOfBalls());
        }
        p.removeFromGame(this);
    }

    /**
     * getScreen.
     *
     * @return the GUI screen.
     */
    public GUI getScreen() {
        return this.screen;
    }

    /**
     * getHeightOfScreen.
     *
     * @return the height of the screen.
     */
    public int getHeightOfScreen() {
        return this.screen.getDrawSurface().getHeight();
    }

    /**
     * getWidthOfScreen.
     *
     * @return the width of the screen.
     */
    public int getWidthOfScreen() {
        return this.screen.getDrawSurface().getWidth();
    }

    /**
     * getNumOfBalls.
     *
     * @return number of balls currently on the screen.
     */
    public int getNumOfBalls() {
        return this.numOfBalls.getValue();
    }

    /**
     * getNumOfBlocks.
     *
     * @return number of blocks currently on the screen.
     */
    public int getNumOfBlocks() {
        return this.numOfBlocks.getValue();
    }

    /**
     * getScore.
     *
     * @return the game's score.
     */
    public int getScore() {
        return this.score.getValue();
    }

    /**
     * getNumOfLives.
     *
     * @return the player's number of lives left.
     */
    public int getNumOfLives() {
        return this.numOfLives.getValue();
    }

    /**
     * getKeyboard.
     *
     * @return the game's keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return this.screen.getKeyboardSensor();
    }

    /**
     * getLevelName.
     *
     * @return a string of the level's name.
     */
    public String getLevelName() {
        return this.level.levelName();
    }

    /**
     * ballsCreator.
     * creates the balls according to the game's information.
     */
    public void ballsCreator() {
        List<Velocity> velocities = level.initialBallVelocities();
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball b = new Ball(new Point(screen.getDrawSurface().getWidth() / 2,
                    screen.getDrawSurface().getHeight() - 60), 6, Color.white);
            b.setVelocity(velocities.get(i));
            b.addToGame(this);
            b.setGameEnvironment(this.environment);
        }
    }

    /**
     * setBlocksBoundaries.
     *
     * @return and array of 3 boundaries blocks.
     */
    public Block[] setBlocksBoundaries() {
        Block[] blocksBoundaries = new Block[3];
        blocksBoundaries[0] = new Block(new Point(0, 30), getWidthOfScreen(), 25,
                Color.gray);
        blocksBoundaries[1] = new Block(new Point(0, 30), 25, getHeightOfScreen(),
                Color.gray);
        blocksBoundaries[2] = new Block(new Point(getWidthOfScreen() - 25, 30), 25,
                getHeightOfScreen(), Color.gray);
        for (Block b : blocksBoundaries) {
            b.setNumOfHits(-1);
        }
        return blocksBoundaries;
    }

    /**
     * paddleCreator.
     *
     * @return a new game paddle.
     */
    public Paddle paddleCreator() {
        int height = 18;
        int width = level.paddleWidth();
        double x = 400 - (width / 2);
        double y = getHeightOfScreen() - height - 31;
        Paddle p = new Paddle(this, new Block(new Point(x, y),
                level.paddleWidth(), height, Color.yellow), level.paddleSpeed());
        p.addToGame(this);
        return p;
    }
}