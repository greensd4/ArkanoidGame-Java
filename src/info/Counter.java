package info;

/**
 * Counter.
 *
 * @author Daniel Greenspan.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     *
     * @param n - a number to start the counter with.
     */
    public Counter(int n) {
        this.counter = n;
    }

    /**
     * increase.
     *
     * @param number - a number to increase the counter by.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * decrease.
     *
     * @param number - a number to decrease the counter by.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * getValue.
     *
     * @return - the current value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}