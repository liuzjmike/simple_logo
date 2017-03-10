package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.Constants;

/**
 * Returns sine of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Sin extends AbstractCommand {

	public Sin() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Constants.resolveNaN(Math.sin(getParamValue(env, 0) * Constants.RADIAN_PER_DEGREE));
	}

}