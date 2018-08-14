package listeners;

/**
 * HitNotifier.
 *
 * @author Daniel Greenspan.
 */
public interface HitNotifier {

    /**
     * addHitListener.
     * Add hl as a listener to hit events.
     *
     * @param hl - a listener to ba added.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener.
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - a listener to be removed.
     */
    void removeHitListener(HitListener hl);
}
