package model.executable.command.math;

public class NotEqual extends LogicCommand {

	public NotEqual() {
		super(2);
	}

	@Override
	protected boolean logic(double arg0, double arg1) {
		return arg0 != arg1;
	}
}
