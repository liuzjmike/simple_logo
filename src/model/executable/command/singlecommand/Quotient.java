package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;
import util.SLogoException;

/**
 * Returns quotient of the values of expr1 and expr2 Throws exception if expr2
 * is equal to 0
 * 
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
			throw new SLogoException(SLogoException.ZERO_DENOMINATOR);
		}
		return getParamValue(0, env) / getParamValue(1, env);
	}

}
