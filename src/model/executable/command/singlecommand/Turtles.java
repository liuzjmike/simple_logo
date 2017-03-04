package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns number of turtles created so far
 * 
 * @author zhuangbihan
 *
 */
public class Turtles extends AbstractCommand {

	public Turtles() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return env.getTurtlePool().fullSize();
	}

}
