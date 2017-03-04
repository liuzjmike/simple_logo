package model.executable.command.noparam;

import model.Environment;

/**
 * Reports the number Pi
 * @author zhuangbihan
 *
 */
public class Pi extends NoParamCommand {

	@Override
	protected double concreteExecute(Environment env) {
		return Math.PI;
	}

}
