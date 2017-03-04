package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.Constants;

/**
 * Returns tangent of degrees
 * @author zhuangbihan
 *
 */
public class Tan extends AbstractCommand {

	public Tan() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.tan(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE));
	}

}
