package model.executable.command.math;

/**
 * Returns 1 if test is 0 and 0 if test is non-zero
 * 
 * @author zhuangbihan
 *
 */
public class Not extends LogicCommand {

	public Not() {
		super(1);
	}

	@Override
	protected boolean logic(double arg0, double arg1) {
		return arg0 == 0;
	}
}
