package model.executable.command;

import model.Environment;
import model.turtle.Turtle;

public abstract class SingleCommand extends AbstractCommand {

	public SingleCommand(int numParams) {
		super(numParams);
	}

	@Override
	public double run(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtleExecute(turtle));
	}
	
	protected abstract double turtleExecute(Turtle turtle);
}
