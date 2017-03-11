package view;

import java.util.Arrays;
import java.util.List;

import controller.ControlHandler;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

public class PenView extends ScrollView {
	public static final List<String> THICKNESS = Arrays.asList("1", "2", "3", "4", "5");
	public static final List<String> PENDOWN = Arrays.asList("Up","Down");
	public static final String PENSIZE_COMMAND= "SetPenSize %d";
	public static final String PENUP_COMMAND = "PenUp";
	public static final String PENDOWN_COMMAND = "PenDown";
	
	private ControlHandler myHandler;

	public PenView(ControlHandler handler) {
		super("Pen Setting", handler);
		myHandler = handler;
		addAllElements(createComboBox("Pen Size", THICKNESS, (observable, oldValue, newValue) -> {
            setPenSize(Integer.parseInt(newValue));
        }), createComboBox("Pen Down",PENDOWN , (observable, oldValue, newValue) -> {
            setPenDown(newValue);
        }));
	
	}

	private ComboBox<String> createComboBox(String promptText, List<String> content, ChangeListener<String> listener) {
		ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(content));
		cb.setPromptText(promptText);
		cb.getSelectionModel().selectedItemProperty().addListener(listener);
		return cb;
	}
	private void setPenSize(int value){
		myHandler.accept(String.format(PENSIZE_COMMAND,value));
	}
	private void setPenDown(String value){
		if(value.equals("Up")) myHandler.accept(PENUP_COMMAND);
		else if(value.equals("Down")) myHandler.accept(PENDOWN_COMMAND);
	}
}
