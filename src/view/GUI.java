package view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;

import controller.ControlHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.info.PaletteInfo;
import model.info.PoolInfo;
import model.info.ShapeInfo;
import util.Constants;
import util.SLogoException;
import util.SLogoObserver;
import view.factory.ConstraintsFactory;

public class GUI {

    public static final String STYLESHEET = "default.css";

    public static final double HEIGHT_RATIO = 0.9;
    public static final double ASPECT_RATIO = 1.3;
    public static final double LEFT_CONSTRAINT = 75;
    public static final double TOP_CONSTRAINT = 55;

    private Stage myStage;
    private GridPane myRoot;

    private PoolView myPoolView;
    private ConsoleView myConsoleView;
    private VariableView myVariableView;
    private CommandView myCommandView;
    private PaletteView myPaletteView;
    private ShapeView myShapeView;

    private ControlHandler myHandler;
    private Function<String, Double> guiHandler;

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

    public SLogoObserver<List<Entry<String, Double>>> getVariableObserver() {
        return myVariableView;
    }

    public SLogoObserver<List<String>> getCommandObserver() {
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
        root.setPrefHeight(gd.getDisplayMode().getHeight() * HEIGHT_RATIO);
        root.setPrefWidth(root.getPrefHeight() * ASPECT_RATIO);

        ConstraintsFactory cf = new ConstraintsFactory();
        root.getColumnConstraints().addAll(cf.getColumnConstraints(LEFT_CONSTRAINT),
                                           cf.getColumnConstraints(100 - LEFT_CONSTRAINT));
        root.getRowConstraints().addAll(cf.getRowConstraints(TOP_CONSTRAINT),
                                        cf.getRowConstraints(100 - TOP_CONSTRAINT));
        return root;
    }
    
    private void initView() {
        myPoolView = new PoolView(getPoolWidth(), getPoolHeight(), guiHandler, new ViewSupplier() {

			@Override
			public PaletteInfo getPalette() {
				return myPaletteView.getPalette();
			}

			@Override
			public ShapeInfo getShapeInfo() {
				return myShapeView;
			}
        	
        });
        myConsoleView = new ConsoleView(getPoolWidth(), guiHandler);
        myVariableView = new VariableView(guiHandler);
        myCommandView = new CommandView(guiHandler);
        myPaletteView = new PaletteView(guiHandler);
        myShapeView = new ShapeView(guiHandler);

        myRoot.add(myPoolView.getRoot(), 0, 0, 1, 1);
        myRoot.add(myConsoleView.getRoot(), 0, 1, 1, 1);
        myRoot.add(createTabPane(myVariableView, myCommandView, new PenView(myHandler)), 1, 0, 1, 1);
        myRoot.add(createTabPane(myPaletteView, myShapeView, new OptionView(myHandler)), 1, 1, 1, 1);
    }

    private Scene createScene() {
        Scene scene = new Scene(myRoot);
        scene.getStylesheets().add(Constants.DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
        scene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            if(!myConsoleView.handleKeyInput(code)) {
                myPoolView.handleKeyInput(code, myStage);
            }
        });
        scene.setOnKeyReleased(e -> myPoolView.handleRelease(e.getCode(), myStage));
        return scene;
    }

    private Function<String, Double> createHandler(ControlHandler handler) {
        return command -> {
            double ret = 0;
            if (!command.isEmpty()) {
                try {
                    ret = handler.apply(command);
                } catch (SLogoException e) {
                    Alert alert = new Alert(AlertType.ERROR, e.getMessage());
                    alert.show();
                }
                myConsoleView.addCommandHist(command, ret);
            }
            return ret;
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
        Tab ret = new Tab(view.getName(), view.getRoot());
        ret.setClosable(false);
        return ret;
    }
}