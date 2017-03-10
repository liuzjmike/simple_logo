package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Makes turtle invisible Returns 0
 * 
 * @author zhuangbihan
 *
 */
public class HideTurtle extends MultipleCommand {

	public HideTurtle() {
		super(0);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		turtle.setVisible(false);
		return 0;
	}

}
