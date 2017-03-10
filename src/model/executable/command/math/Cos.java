package model.executable.command.math;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.Constants;

/**
 * Return cosine of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Cos extends AbstractCommand {

	public Cos() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Constants.resolveNaN(Math.cos(getParamValue(env, 0) * Constants.RADIAN_PER_DEGREE));
	}

}
