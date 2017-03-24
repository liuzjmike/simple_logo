package view;

import java.util.Collection;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

interface Drawer {

    void addLine(Line line);
    
    void removeLines(Collection<Line> lines);
    
    void addTurtleImage(ImageView iv);
    
    void removeTurtleImage(ImageView iv);
}
