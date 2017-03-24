package view;

import java.util.function.Function;

import javafx.scene.Parent;

/**
 * 
 * @author Mike Liu
 *
 */
public abstract class View<T extends Parent> {
    
    private String myName;
    private T myRoot;
    private Function<String, Double> myHandler;

    public View(String name, T root, Function<String, Double> guiHandler) {
        myName = name;
        myRoot = root;
        myHandler = guiHandler;
    }
    
    public String getName() {
        return myName;
    }
    
    public T getRoot() {
        return myRoot;
    }
    
    protected double execute(String command) {
        return myHandler.apply(command);
    }
}
