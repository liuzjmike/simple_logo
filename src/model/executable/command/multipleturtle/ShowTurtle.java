package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Makes turtle visible Returns 1
 * 
 * @author zhuangbihan
 *
 */
public class ShowTurtle extends MultipleCommand {

	public ShowTurtle() {
		super(0);
	}
	
	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		turtle.setVisible(true);
		return 1;
	}

}
