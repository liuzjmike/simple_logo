package model.executable.command.structure;

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
	protected double run(Environment env) {
		return env.getTurtlePool().fullSize();
	}

}
