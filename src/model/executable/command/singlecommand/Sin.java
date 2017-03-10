package model.executable.command.singlecommand;

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
	protected double concreteExecute(Environment env) {
		return Constants.resolveNaN(Math.sin(getParamValue(0, env) * Constants.RADIAN_PER_DEGREE));
	}

}
