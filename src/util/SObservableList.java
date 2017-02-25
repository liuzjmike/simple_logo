package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SObservableList<E> extends SLogoObservable<List<E>> implements Iterable<E> {
    
    private List<E> myList;
    
    public SObservableList() {
        myList = new ArrayList<>();
    }
    
    public void add(E e) {
        myList.add(e);
        notifyObservers();
    }
    
    public void addAll(Collection<? extends E> c) {
        myList.addAll(c);
        notifyObservers();
    }
    
    public boolean contains(Object o) {
        return myList.contains(o);
    }
    
    public E get(int index) {
        return myList.get(index);
    }
    
    public List<E> getAll() {
        return Collections.unmodifiableList(myList);
    }
    
    public E remove(int index) {
        E ret = myList.remove(index);
        notifyObservers();
        return ret;
    }
    
    public boolean remove(Object o) {
        boolean ret = myList.remove(o);
        notifyObservers();
        return ret;
    }

    @Override
    public Iterator<E> iterator() {
        return myList.iterator();
    }

    private void notifyObservers() {
        notifyObservers(Collections.unmodifiableList(myList));
    }
}