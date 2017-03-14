package model.executable.command.math;

/**
 * Returns 1 if the value of expr1 is strictly less than the value of expr2,
 * otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class Less extends LogicCommand {

	public Less() {
		super(2);
	}

	@Override
	protected boolean logic(double arg0, double arg1) {
		return arg0 < arg1;
	}

}
