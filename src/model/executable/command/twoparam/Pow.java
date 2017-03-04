package model.executable.command.twoparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns base raised to the power of the exponent
 * @author zhuangbihan
 *
 */
public class Pow extends AbstractCommand {

	public Pow() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return Math.pow(getParamValue(0, env), getParamValue(1, env));
	}

}
