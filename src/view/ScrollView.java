package view;

import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Mike Liu
 *
 */
public class ScrollView extends View<ScrollPane> {
    
    private VBox myBox;

    public ScrollView(String name, Function<String, Double> guiHandler, boolean autoScroll) {
        super(name, new ScrollPane(), guiHandler);
        myBox = new VBox();
        myBox.setId("big-container");
        getRoot().setContent(myBox);
        if(autoScroll) {
            myBox.heightProperty().addListener((observable, oldValue, newValue) -> {
                getRoot().setVvalue(1);
            });
        }
    }
    
    public ScrollView(String name, String id, Function<String, Double> guiHandler, boolean autoScroll) {
        this(name, guiHandler, autoScroll);
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
    
    public void setPrefWidth(double width) {
        myBox.setPrefWidth(width);
    }
    
    public void setPrefHeight(double height) {
        myBox.setPrefHeight(height);
    }

}
