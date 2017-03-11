package view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.info.PaletteInfo;
import model.info.TurtleInfo;
import model.turtle.TurtleHist;
import util.Constants;

public class TurtleView {

    public static final double DEFAULT_WIDTH = 40;
    public static final double DEFAULT_HEIGHT = 36;
    public static final String ID_INFO = "Turtle ID: %d";
    public static final String XPOS_INFO = "X Position: %f";
    public static final String YPOS_INFO = "Y Position: %f";
    public static final String HEADING_INFO = "Heading: %f";
    public static final String PEN_INFO = "Pen: %s";
    public static final String DOWN = "Down";
    public static final String UP = "Up";
    public static final double BOX_HEIGHT = 125;
    
    private ImageView myImage;
    private TurtleInfo myTurtle;
    private List<Line> myLines;
    private LineDrawer lineDrawer; 
    private Popup myInfoWindow;
    private ListView<String> infoBox;
    private double xOffset, yOffset;

    double newTranslateX;
    double newTranslateY;

    public TurtleView(ImageView image, TurtleInfo turtle, LineDrawer lineDrawer,
            double xOffset, double yOffset) {
        myImage = image;
        setHeading(turtle.getHeading());
        setSize(DEFAULT_HEIGHT, DEFAULT_WIDTH);
        setVisible(turtle.isVisible());
        myTurtle = turtle;
        myLines = new ArrayList<Line>();
        this.lineDrawer = lineDrawer;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        myInfoWindow = new Popup();
        infoBox = new ListView<String>();
        myInfoWindow.getContent().addAll(infoBox);
    }

    public void update(PaletteInfo palette, List<ImageView> shapes) {
        setVisible(myTurtle.isVisible());
        setHeading(myTurtle.getHeading());
        List<TurtleHist> lastMove = myTurtle.getLastMove();
        for(int i = 0; i < lastMove.size() - 1; i++) {
            myLines.clear();
            TurtleHist oldHist = lastMove.get(i), newHist = lastMove.get(i+1);
            newHist = lastMove.get(i+1);
            if(oldHist.penDown()) {
                Line line = myTurtle.getPenInfo().drawLine(palette,
                                                           transformX(oldHist.getX()),
                                                           transformY(oldHist.getY()),
                                                           transformX(newHist.getX()), 
                                                           transformY(newHist.getY()));
                lineDrawer.addLine(line);
                myLines.add(line);
            }
        }
        setXY(myTurtle.getX(), myTurtle.getY());
        if(myTurtle.isReset()) {
            lineDrawer.removeLines(myLines);
            myLines.clear();
        }
    }

    private void setVisible(boolean isVisible){
        myImage.setVisible(isVisible);
    }

    private void setSize(double width, double height){
        myImage.setFitWidth(width);
        myImage.setFitHeight(height);
    }

    /*****Translational movement*****/
    private void setXY(double x, double y) {
        myImage.setX(transformX(x) - DEFAULT_WIDTH / 2);
        myImage.setY(transformY(y) - DEFAULT_HEIGHT / 2);
    }

    /*****Rotational movement*****/
    private void setHeading(double heading){
        myImage.setRotate((-heading+90)%Constants.ROUND_ANGLE);
    }

    private double transformX(double x) {
        return x + xOffset;
    }

    private double transformY(double y) {
        return - y + yOffset;
    }

    public ImageView getImageView() {
        return myImage;
    }
    
    public void setPopUp(Integer id, Stage stage){
    	String penDown = myTurtle.getPenInfo().isDown() ? DOWN:UP;
		ObservableList<String> items = FXCollections.observableArrayList(String.format(ID_INFO, id),String.format(XPOS_INFO, myTurtle.getX()), String.format(YPOS_INFO, myTurtle.getY()), String.format(HEADING_INFO, myTurtle.getHeading()), String.format(PEN_INFO,penDown ));
		infoBox.setItems(items);
		infoBox.setPrefHeight(BOX_HEIGHT);
		infoBox.setLayoutX(myImage.getX()-xOffset);
		infoBox.setLayoutY(myImage.getY()-yOffset);
		myInfoWindow.show(stage);
    }
    public void hidePopUp(Stage stage){
    	myInfoWindow.hide();
    }

}
