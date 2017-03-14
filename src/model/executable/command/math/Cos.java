package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;
import util.Constants;

/**
 * Return cosine of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Cos extends ActionCommand {

	public Cos() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		return Constants.resolveNaN(Math.cos(getParamValue(env, 0) * Constants.RADIAN_PER_DEGREE));
	}

}
