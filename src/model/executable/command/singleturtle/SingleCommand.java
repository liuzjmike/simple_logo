package model.executable.command.singleturtle;

import model.Environment;
import model.executable.command.ActionCommand;
import model.turtle.Turtle;

public abstract class SingleCommand extends ActionCommand {

	public SingleCommand(int numParams) {
		super(numParams);
	}

	@Override
	public double run(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtleExecute(turtle));
	}
	
	protected abstract double turtleExecute(Turtle turtle);
}
