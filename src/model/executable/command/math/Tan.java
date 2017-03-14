package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;
import util.Constants;

/**
 * Returns tangent of degrees
 * 
 * @author zhuangbihan
 *
 */
public class Tan extends ActionCommand {

	public Tan() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		// TODO Auto-generated method stub
		return Constants.resolveNaN(Math.tan(getParamValue(env, 0) * Constants.RADIAN_PER_DEGREE));
	}

}
