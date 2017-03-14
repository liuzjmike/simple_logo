package model.executable.command.math;

/**
 * Returns 1 if test1 or test2 are non-zero, otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class Or extends LogicCommand {

	public Or() {
		super(2);
	}

	@Override
	protected boolean logic(double arg0, double arg1) {
		return arg0!=0 || arg1!=0;
	}

}
