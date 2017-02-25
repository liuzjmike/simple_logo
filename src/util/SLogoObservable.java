package util;

import java.util.ArrayList;
import java.util.List;

public class SLogoObservable<T> {
    
    private List<SLogoObserver<T>> myObservers;
    
    public SLogoObservable() {
        myObservers = new ArrayList<>();
    }
    
    public void addObserver(SLogoObserver<T> so) {
        myObservers.add(so);
    }
    
    public void removeObserver(SLogoObserver<T> so) {
        myObservers.remove(so);
    }
    
    public void notifyObservers(T arg) {
        for(SLogoObserver<T> so : myObservers) {
            so.update(arg);
        }
    }

}
