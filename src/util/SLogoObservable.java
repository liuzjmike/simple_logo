package util;

import java.util.HashSet;
import java.util.Set;

public abstract class SLogoObservable<T> {
    
    private Set<SLogoObserver<T>> myObservers;
    
    public SLogoObservable() {
        myObservers = new HashSet<>();
    }
    
    public void addObserver(SLogoObserver<T> so) {
        myObservers.add(so);
        so.update(notification());
    }
    
    public void removeObserver(SLogoObserver<T> so) {
        myObservers.remove(so);
    }
    
    public void notifyObservers() {
        notifyObservers(notification());
    }
    
    public void notifyObservers(T arg) {
        for(SLogoObserver<T> so : myObservers) {
            so.update(arg);
        }
    }
    
    protected abstract T notification();

}
