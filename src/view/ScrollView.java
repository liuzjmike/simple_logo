package view;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ScrollView extends View<ScrollPane> {
    
    private VBox myBox;

    public ScrollView(String name, Consumer<String> guiHandler) {
        super(name, new ScrollPane(), guiHandler);
        myBox = new VBox();
        getRoot().setContent(myBox);
    }
    
    public ScrollView(String name, String id, Consumer<String> guiHandler) {
        this(name, guiHandler);
        myBox.setId(id);
    }
    
    public void addElement(Node node) {
        myBox.getChildren().add(node);
    }
    
    public void addAllElements(Node...nodes) {
    	myBox.getChildren().addAll(nodes);
    }
    
    public void removeElement(Node node) {
        myBox.getChildren().remove(node);
    }
    
    public void clear() {
        myBox.getChildren().clear();
    }
    
    public void setId(String id) {
    	myBox.setId(id);
    }

}
