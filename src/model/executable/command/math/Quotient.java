package model.executable.command.math;

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
	protected double run(Environment env) {
		if (getParamValue(env, 1) == 0) {
			throw new SLogoException(SLogoException.ZERO_DENOMINATOR);
		}
		return getParamValue(env, 0) / getParamValue(env, 1);
	}

}