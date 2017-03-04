package model.executable.command.twoparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns quotient of the values of expr1 and expr2
 * Throws exception if expr2 is equal to 0
 * @author zhuangbihan
 *
 */
public class Quotient extends AbstractCommand {

	public Quotient() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		if (getParamValue(1, env) == 0) {
			throw new RuntimeException();
		}
		return getParamValue(0, env)/getParamValue(1, env);
	}

}
