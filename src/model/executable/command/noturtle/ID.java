package model.executable.command.noturtle;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns current active turtle's ID number
 * 
 * @author zhuangbihan
 *
 */
public class ID extends ActionCommand {

	public ID() {
		super(0);
	}

	@Override
	public double run(Environment env) {
		return env.getTurtlePool().activeID();
	}
}
