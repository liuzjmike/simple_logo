package model.executable.command.math;

import model.Environment;
import util.Constants;

public abstract class TrigCommand extends MathCommand {

	public TrigCommand(int numParams) {
		super(numParams);
	}

	@Override
	protected double run(Environment env) {
		return Constants.resolveNaN(trig(getParamValue(env, lastParamIndex()) * Constants.RADIAN_PER_DEGREE));
	}
	
	protected abstract double trig(double arg);
}
