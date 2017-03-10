package model.executable.command.singleturtle;

import model.executable.command.SingleCommand;
import model.turtle.Turtle;

public class PenColor extends SingleCommand {

	public PenColor() {
		super(0);
	}

	@Override
	protected double turtleExecute(Turtle turtle) {
		return turtle.getPen().getColor();
	}

}
