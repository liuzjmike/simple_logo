package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Reports the number Pi
 * 
 * @author zhuangbihan
 *
 */
public class Pi extends AbstractCommand {

	public Pi() {
		super(0);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Math.PI;
	}

}
