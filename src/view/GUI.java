package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import controller.ControlHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.executable.Literal;
import model.executable.command.Command;
import model.info.PaletteInfo;
import model.info.PoolInfo;
import util.Constants;
import util.SLogoObserver;

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
    
    public Color getBackgroundColor() {
        return myPoolView.getBackgroundColor();
    }
    
    public void setBackgroundColor(Color color) {
        myPoolView.setBackgroundColor(color);
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
        myRoot.add(createTabPane(myVariableView, myPaletteView, new OptionView(myHandler)), 1, 0, 1, 1);
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