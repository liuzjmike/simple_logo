package model.executable.command.math;

/**
 * Returns 1 if the value of expr1 and the value of expr2 are equal, otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class Equal extends LogicCommand {

	public Equal() {
		super(2);
	}

	@Override
	protected boolean logic(double arg0, double arg1) {
		return arg0==arg1;
	}

}
