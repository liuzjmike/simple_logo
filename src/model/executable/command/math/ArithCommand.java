package model.executable.command.math;

import model.Environment;
import model.executable.command.MathCommand;

public abstract class ArithCommand extends MathCommand {

	public ArithCommand(int numParams) {
		super(numParams);
	}
	
	@Override
	protected double run(Environment env) {
		double result = 0;
		for(int i = 0; i < paramsLength(); i++) {
			result = arithmetic(result, getParamValue(env, i));
		}
		return result;
	}
	
	protected abstract double arithmetic(double arg0, double arg1);
}
