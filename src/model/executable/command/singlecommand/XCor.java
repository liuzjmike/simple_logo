package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns the turtle's X coordinate from the center of the screen
 * 
 * @author zhuangbihan
 *
 */
public class XCor extends AbstractCommand {

	public XCor() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().apply(turtle -> turtle.getX());
	}

}
