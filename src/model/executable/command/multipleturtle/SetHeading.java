package model.executable.command.multipleturtle;

import model.Environment;
import model.executable.command.MultipleCommand;
import model.turtle.Turtle;

/**
 * Turns turtle to an absolute heading
 * @author zhuangbihan
 *
 */
public class SetHeading extends MultipleCommand {

	public SetHeading() {
		super(1);
	}

	@Override
	protected double turtleExecute(Environment env, Turtle turtle) {
		return turtle.setHeading(getParamValue(env,0));
	}

}
