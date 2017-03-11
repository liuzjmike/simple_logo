package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;

import javax.xml.transform.TransformerException;

import controller.ControlHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.executable.Literal;
import model.executable.command.Command;
import model.info.PaletteInfo;
import model.info.PoolInfo;
import util.Constants;
import util.SLogoException;
import util.SLogoObserver;
import util.XMLParserWriter;

public class GUI {

    public static final String STYLESHEET = "default.css";

    public static final double SCREEN_RATIO = 0.9;
    public static final double LEFT_CONSTRAINT = 85;
    public static final double TOP_CONSTRAINT = 60;

    private Stage myStage;
    private GridPane myRoot;

    private PoolView myPoolView;
    private ConsoleView myConsoleView;
    private VariableView myVariableView;
    private CommandView myCommandView;
    private PaletteView myPaletteView;

    private ControlHandler myHandler;
    private Consumer<String> guiHandler;
    

    public enum FileType {COMMANDS, SAVED_STATE}

    public GUI(Stage stage, ControlHandler handler) {
        myRoot = createRoot();
        myHandler = handler;
        guiHandler = createHandler(handler);
        initView();
        stage.setScene(createScene());
        stage.setResizable(false);
        myStage = stage;
    }

    public void show() {
        myStage.show();
    }

    public double getPoolWidth() {
        return myRoot.getPrefWidth() * LEFT_CONSTRAINT / 100;
    }

    public double getPoolHeight() {
        return myRoot.getPrefHeight() * TOP_CONSTRAINT / 100; 
    }

    public SLogoObserver<PoolInfo> getPoolObserver() {
        return myPoolView;
    }

    public SLogoObserver<List<Entry<String, Literal>>> getVariableObserver() {
        return myVariableView;
    }

    public SLogoObserver<Map<String, Command>> getCommandObserver() {
        return myCommandView;
    }
    
    public SLogoObserver<PaletteInfo> getPaletteObserver() {
    	return myPaletteView;
    }

    private GridPane createRoot() {
        final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        GridPane root = new GridPane();
        root.setPrefWidth(gd.getDisplayMode().getWidth() * SCREEN_RATIO);
        root.setPrefHeight(gd.getDisplayMode().getHeight() * SCREEN_RATIO);

        root.getColumnConstraints().addAll(getColumnConstraints(LEFT_CONSTRAINT),
                                             getColumnConstraints(100 - LEFT_CONSTRAINT));
        root.getRowConstraints().addAll(getRowConstraints(TOP_CONSTRAINT),
                                          getRowConstraints(100 - TOP_CONSTRAINT));
        return root;
    }
    
    private void initView() {
        myPoolView = new PoolView(getPoolWidth(), getPoolHeight(), guiHandler, () -> {
        	return myPaletteView.getPalette();
        });
        myConsoleView = new ConsoleView(guiHandler);
        myVariableView = new VariableView(guiHandler);
        myCommandView = new CommandView(guiHandler);
        myPaletteView = new PaletteView(guiHandler);

        myRoot.add(myPoolView.getRoot(), 0, 0, 1, 1);
        myRoot.add(myConsoleView.getRoot(), 0, 1, 1, 1);
        myRoot.add(createTabPane(myVariableView, myPaletteView), 1, 0, 1, 1);
        myRoot.add(createTabPane(myCommandView), 1, 1, 1, 1);
        //TODO: Add new tabs here
    }

    private Scene createScene() {
        Scene scene = new Scene(myRoot);
        scene.getStylesheets().add(Constants.DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        scene.setOnKeyPressed(e -> myPoolView.handleKeyInput(e.getCode(), myStage));
        scene.setOnKeyReleased(e -> myPoolView.handleRelease(e.getCode(), myStage));
        return scene;
    }

    private Consumer<String> createHandler(ControlHandler handler) {
        return command -> {
            if (!command.isEmpty()) {
                try {
                    handler.accept(command);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                myConsoleView.addCommandToScreen(command);
            }
        };
    }

    private HBox getUserBar1() {
        HBox userBar = new HBox();
        userBar.setAlignment(Pos.CENTER_LEFT);
        Button changeColorButton = new Button("Change Background Color");
        changeColorButton.setOnMouseClicked(onClick -> promptForBackgroundColorChange());
        Button showReferenceButton = new Button("SLogo Reference");
        showReferenceButton.setOnMouseClicked(onClick -> promptForReference());
        Button changeLanguageButton = new Button("Change Language");
        changeLanguageButton.setOnMouseClicked(onClick -> promptLanguage());
        ObservableList<String> options = FXCollections.observableArrayList(
                "Thin",
                "Moderate",
                "Thick"
                );
        ComboBox<String> penSettingBox = new ComboBox<>(options);
        penSettingBox.setPromptText("Pen Thickness");
        //penSettingButton.setOnMouseClicked(onClick -> setPenSetting());


        userBar.getChildren().addAll(changeColorButton,showReferenceButton,changeLanguageButton,penSettingBox);
        return userBar;
    }
    
    private HBox getUserBar2() {
        HBox userBar = new HBox();
        userBar.setAlignment(Pos.CENTER_LEFT);
        Button newWorkspace = new Button("Create New Workspace");
        newWorkspace.setOnMouseClicked(onClick -> myHandler.newWorkspace());
        Button saveState = new Button("Save Workspace Settings");
        saveState.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    saveWorkspacePref();
                } catch (TransformerException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button extractState = new Button("Get Saved Workspace Settings");
        extractState.setOnMouseClicked(onClick -> extractState());
        Button readCommandsFromFile = new Button("Read Commands From File");
        readCommandsFromFile.setOnAction(onClick -> executeCommandsFromFile());
        userBar.getChildren().addAll(newWorkspace,saveState,extractState,readCommandsFromFile);
        return userBar;
    }

    private void promptForReference() {
        HelpViewer myHelpViewer = new HelpViewer("reference.html");
        try {
            myHelpViewer.start(new Stage());
        } catch (Exception e) {
            throw new SLogoException(SLogoException.HELP_VIEWER_FAILED);
        }
    }

    private void addButtons(Alert alert, String[] strings) {
        for (String str : strings) {
            ButtonType button = new ButtonType(str);
            alert.getButtonTypes().add(button);
        }
    }

    private void addCancelButton(Alert alert) {
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().add(buttonTypeCancel);
    }

    private void promptLanguage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Change Language");
        alert.setHeaderText(null);
        alert.setContentText("Choose a language");

        String[] buttons = new String[] {"Chinese","English","French","German","Italian","Portugese","Russian","Spanish"};

        addButtons(alert,buttons);
        addCancelButton(alert);

        ButtonType buttonSelected = alert.showAndWait().get();
        myHandler.setLanguage(buttonSelected.getText());
        myStage.toFront();
    }

    private void promptForBackgroundColorChange() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Change Background Color");
        alert.setHeaderText(null);
        alert.setContentText("Choose a color");

        String[] buttons = new String[] {"Blue","Black","Red"};

        addButtons(alert,buttons);
        addCancelButton(alert);

        ButtonType result = alert.showAndWait().get();

        if (result.getText().equals("Blue")){
            myPoolView.setBackgroundColor(Color.BLUE);
        } else if (result.getText().equals("Black")) {
            myPoolView.setBackgroundColor(Color.BLACK);
        } else if (result.getText().equals("Red")) {
            myPoolView.setBackgroundColor(Color.RED);
        } else {
            throw new SLogoException(SLogoException.INVALID_COLOR_BUTTON);
        }
        myStage.toFront();
    }

    private void saveWorkspacePref() throws TransformerException, IOException {
        String fileName = promptUserForFileName();
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("Background color", myPoolView.getBackgroundColor().toString());
        parameters.put("Language", myHandler.getLanguage());
        XMLParserWriter.saveState(fileName, "Workspace", parameters, "SavedStates");
    }

    private File promptUserForFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(new Stage());
        return file;
    }

    private String promptUserForFileName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Select File Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Type in the name of the file: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    private Map<String,String> getFileContents(boolean orderMatters) {
        File file = promptUserForFile();
        return XMLParserWriter.extractContent(file,orderMatters);
    }

    private void extractState() {
        Map<String,String> parameters = getFileContents(false);
        myPoolView.setBackgroundColor(Color.web(parameters.get("color")));
        myHandler.setLanguage(parameters.get("language"));
    }

    public void executeCommandsFromFile() {
        Map<String,String> commands = getFileContents(true);
        for (String command : commands.keySet()) {
            myConsoleView.addCommandToScreen(commands.get(command));
            myHandler.accept(commands.get(command));
        }
    }
    
    private TabPane createTabPane(View<?>...views) {
        TabPane ret = new TabPane();
        for(int i = 0; i < views.length; i++) {
            ret.getTabs().add(createTab(views[i]));
        }
        return ret;
    }
    
    private Tab createTab(View<?> view) {
        return new Tab(view.getName(), view.getRoot());
    }

    private ColumnConstraints getColumnConstraints(double percent) {
        ColumnConstraints ret = new ColumnConstraints();
        ret.setHgrow(Priority.NEVER);
        ret.setPercentWidth(percent);
        return ret;
    }

    private RowConstraints getRowConstraints(double percent) {
        RowConstraints ret = new RowConstraints();
        ret.setVgrow(Priority.NEVER);
        ret.setPercentHeight(percent);
        return ret;
    }
}