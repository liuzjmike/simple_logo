package view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public abstract class ScrollView extends View<ScrollPane> {
    
    private VBox myBox;

    public ScrollView(String name) {
        super(name, new ScrollPane());
        myBox = new VBox();
        getRoot().setContent(myBox);
    }
    
    protected void addElement(Node node) {
        myBox.getChildren().add(node);
    }
    
    protected void removeElement(Node node) {
        myBox.getChildren().remove(node);
    }
    
    protected void clear() {
        myBox.getChildren().clear();
    }

}
