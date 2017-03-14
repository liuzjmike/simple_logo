package model.executable.command.math;

import util.SLogoException;

/**
 * Returns quotient of the values of expr1 and expr2 Throws exception if expr2
 * is equal to 0
 * 
 * @author zhuangbihan
 *
 */
public class Quotient extends ArithCommand {

	public Quotient() {
		super(2);
	}

	@Override
	protected double arithmetic(double arg0, double arg1) {
		if(arg1 == 0) {
			throw new SLogoException(SLogoException.ZERO_DENOMINATOR);
		}
		return arg0 / arg1;
	}

}
