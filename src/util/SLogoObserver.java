package util;

/**
 * A class can implement the <code>SLogoObserver</code> interface
 * when it wants to be informed of changes in <code>SlogoObservable</code> objects.
 * @author Mike Liu
 *
 * @param <T>
 */
public interface SLogoObserver<T> {

    void update(T arg);
}
