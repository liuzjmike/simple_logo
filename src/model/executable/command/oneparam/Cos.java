package model.executable.command.oneparam;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.Constants;

/**
 * Return cosine of degrees
 * @author zhuangbihan
 *
 */
public class Cos extends AbstractCommand {

	public Cos() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.cos(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE));
	}

}
