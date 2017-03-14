package model.executable.command.noturtle;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns number of turtles created so far
 * 
 * @author zhuangbihan
 *
 */
public class Turtles extends ActionCommand {

	public Turtles() {
		super(0);
	}

	@Override
	protected double run(Environment env) {
		return env.getTurtlePool().fullSize();
	}

}
