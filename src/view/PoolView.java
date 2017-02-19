package view;

import java.util.Collection;
import java.util.Observer;

import model.turtle.Turtle;

public interface PoolView extends Observer {

    public void setTurtle(Collection<Turtle> turtles);
}
