package view;

import java.util.function.Consumer;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class View<T extends Parent> {
    
    private String myName;
    private T myRoot;
    private Consumer<String> myHandler;

    public View(String name, T root, Consumer<String> guiHandler) {
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
    
    protected void execute(String command) {
        if(myHandler == null) {
            Alert alert = new Alert(AlertType.ERROR, "Handler not Initialized");
            alert.show();
        }
        myHandler.accept(command);
    }
}
