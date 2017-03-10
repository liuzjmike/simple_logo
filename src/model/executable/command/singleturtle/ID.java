package model.executable.command.singleturtle;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns current active turtle's ID number
 * 
 * @author zhuangbihan
 *
 */
public class ID extends AbstractCommand {

	public ID() {
		super(0);
	}

	@Override
	public double run(Environment env) {
		return env.getTurtlePool().activeID();
	}
}
