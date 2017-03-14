package model.executable.command.math;

/**
 * Returns sum of the values of expr1 and expr2
 * 
 * @author zhuangbihan
 *
 */
public class Sum extends ArithCommand {

	public Sum() {
		super(2);
	}

	@Override
	protected double arithmetic(double arg0, double arg1) {
		return arg0 + arg1;
	}
}
