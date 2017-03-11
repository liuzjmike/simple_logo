package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.FileSelector;
import view.factory.ConstraintsFactory;
import view.factory.ControlFactory;

public class ConsoleView extends View<GridPane> {

    public static final double LEFT_CONSTRAINT = 97;
    public static final double TOP_CONSTRAINT = 70;
    
    public static final String NEWLINE_REGEX = "\\n";
    public static final String NEWLINE = "\n";
    public static final String HIST_PREFIX = ">> ";
    public static final String FILE_EXTENSION = "*.logo";
    
	private ScrollView output;
	private TextArea input;
	private FileSelector mySelector;
	private List<String> history;
	
	public ConsoleView(Function<String, Double> guiHandler) {
	    super("Console", new GridPane(), guiHandler);
		init(guiHandler);
		history = new ArrayList<>();
        mySelector = new FileSelector(FILE_EXTENSION);
	}
    
    public void addCommandHist(String command, double result) {
    	Text text = new Text(commandToHist(command));
    	text.setOnMouseClicked(e -> execute(histToCommand(text.getText())));
    	output.addAllElements(text, new Text(Double.toString(result)));
        output.getRoot().setVvalue(1);
    }
    
    public void putCommand(String command) {
        input.appendText(command);
    }
    
    public boolean handleKeyInput(KeyCode code) {
        if(code == KeyCode.ENTER) {
            run();
            return true;
        }
        return false;
    }
    
    private void init(Function<String, Double> guiHandler) {
        output = new ScrollView("Output", guiHandler);
        input = new TextArea();
        ConstraintsFactory constraintsFact = new ConstraintsFactory();
        getRoot().getColumnConstraints().addAll(constraintsFact.getColumnConstraints(LEFT_CONSTRAINT),
                                                constraintsFact.getColumnConstraints(1 - LEFT_CONSTRAINT));

        getRoot().getRowConstraints().addAll(constraintsFact.getRowConstraints(TOP_CONSTRAINT),
                                             constraintsFact.getRowConstraints(1 - TOP_CONSTRAINT));
        
        ControlFactory controlFact = new ControlFactory();
        VBox inputBox = new VBox(controlFact.createButton("Run", e -> run()),
                                 controlFact.createButton("Load", e -> loadProject()),
                                 controlFact.createButton("Save", e -> saveHist()));
        inputBox.setId("small-container");
        getRoot().add(output.getRoot(),0,0,2,1);
        getRoot().add(input,0,1,1,1);
        getRoot().add(inputBox, 1, 1,1,1);
        
        GridPane.setValignment(inputBox,VPos.CENTER);
        GridPane.setHalignment(inputBox,HPos.CENTER);
    }
    
    private void run() {
        String command = input.getText();
        if(command.length() > 1) {
            execute(command);
            history.add(command + NEWLINE);
        }
        input.clear();
    }
    
    private void loadProject() {
        File file = mySelector.open();
        if(file != null) {
            try {
                putCommand(load(file));
            } catch (IOException e) {
                new Alert(AlertType.ERROR, "Invalid file");
            }
        }
    }
    
    private void saveHist() {
        File file = mySelector.saveTo(null);
        if(file != null) {
            try {
                save(file);
            } catch (IOException e) {
                new Alert(AlertType.ERROR, "Invalid file");
            }
        }
    }
    
    private String load(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(NEWLINE);
        }
        br.close();
        return sb.toString();
    }
    
    private void save(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for(String command: history) {
            fw.write(command);
        }
        fw.flush();
        fw.close();
    }
    
    private String commandToHist(String command) {
        return processCommand(command, line -> HIST_PREFIX + line);
    }
    
    private String histToCommand(String hist) {
        return processCommand(hist, line -> line.substring(HIST_PREFIX.length()));
    }
    
    private String processCommand(String target, UnaryOperator<String> mapper) {
        return Arrays.stream(target.split(NEWLINE_REGEX))
                .map(mapper)
                .collect(Collectors.joining(NEWLINE));
    }
}
