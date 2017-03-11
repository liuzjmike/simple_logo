package util;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class GUIUtils {
	
	 public static ColumnConstraints getColumnConstraints(double percent) {
	        ColumnConstraints ret = new ColumnConstraints();
	        ret.setHgrow(Priority.NEVER);
	        ret.setPercentWidth(percent);
	        return ret;
	    }

	  public static RowConstraints getRowConstraints(double percent) {
	        RowConstraints ret = new RowConstraints();
	        ret.setVgrow(Priority.NEVER);
	        ret.setPercentHeight(percent);
	        return ret;
	    }
	  
	  public static Button createButton(String text, EventHandler<ActionEvent> handler) {
	        Button button = new Button(text);
	        button.setOnAction(handler);
	        return button;
	    }
	  
	  public static Text createText(String text, EventHandler<MouseEvent> handler) {
		  Text textObj = new Text(text);
		  textObj.setOnMousePressed(handler);
		  return textObj;
	  }
	  
	  public static ComboBox<String> createComboBox(String promptText, List<String> content, ChangeListener<String> listener) {
			ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(content));
			cb.setPromptText(promptText);
			cb.getSelectionModel().selectedItemProperty().addListener(listener);
			return cb;
		}

}
