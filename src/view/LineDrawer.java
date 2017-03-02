package view;

import java.util.Collection;

import javafx.scene.shape.Line;

interface LineDrawer {

    void addLine(Line line);
    
    void removeLines(Collection<Line> lines);
}
