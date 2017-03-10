package view;

import javafx.scene.Parent;

public abstract class View<T extends Parent> {
    
    private String myName;
    private T myRoot;

    public View(String name, T root) {
        myName = name;
        myRoot = root;
    }
    
    public String getName() {
        return myName;
    }
    
    public T getRoot() {
        return myRoot;
    }
}
