package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;
import util.SLogoException;

/**
 * Returns remainder on dividing the values of expr1 by expr2 Throws exception
 * if expr2 is equal to 0
 * 
 * @author zhuangbihan
 *
 */
public class Remainder extends ActionCommand {

	public Remainder() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		if (getParamValue(env, 1) == 0) {
			throw new SLogoException(SLogoException.ZERO_DENOMINATOR);
		}
		return getParamValue(env, 0) % getParamValue(env, 1);
	}

}
