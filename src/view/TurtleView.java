package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import model.info.PaletteInfo;
import model.info.TurtleInfo;
import model.turtle.TurtleHist;
import util.Constants;

public class TurtleView {

    public static final double DEFAULT_WIDTH = 40;
    public static final double DEFAULT_HEIGHT = 36;

    private ImageView myImage;
    private TurtleInfo myTurtle;
    private List<Line> myLines;
    private LineDrawer lineDrawer; 
    private double xOffset, yOffset;

    double newTranslateX;
    double newTranslateY;

    public TurtleView(ImageView image, TurtleInfo turtle, LineDrawer lineDrawer,
            double xOffset, double yOffset) {
        setImage(image);
        setHeading(turtle.getHeading());
        setSize(DEFAULT_HEIGHT, DEFAULT_WIDTH);
        setVisible(turtle.isVisible());
        myTurtle = turtle;
        myLines = new ArrayList<Line>();
        this.lineDrawer = lineDrawer;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void update(PaletteInfo palette) {
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

    private void setImage(ImageView image) {
        myImage = image;
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

}
