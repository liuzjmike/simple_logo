package view;

import java.util.Arrays;
import java.util.List;

import controller.ControlHandler;
import view.factory.ControlFactory;

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
		ControlFactory cf = new ControlFactory();
		addAllElements(cf.createComboBox("Pen Size", THICKNESS, (observable, oldValue, newValue) -> {
            setPenSize(Integer.parseInt(newValue));
        }), cf.createComboBox("Pen Down",PENDOWN , (observable, oldValue, newValue) -> {
            setPenDown(newValue);
        }));
	
	}
	
	private void setPenSize(int value){
		myHandler.apply(String.format(PENSIZE_COMMAND,value));
	}
	private void setPenDown(String value){
		if(value.equals("Up")) myHandler.apply(PENUP_COMMAND);
		else if(value.equals("Down")) myHandler.apply(PENDOWN_COMMAND);
	}
}
