package model.executable.command.math;

import model.Environment;
import model.executable.Literal;
import model.executable.command.AbstractCommand;

public abstract class MathCommand extends AbstractCommand {

	public MathCommand(int numParams) {
		super(numParams);
	}
	
	@Override
	public Literal execute(Environment env) {
		checkParamsLength();
		env.getVariablePool().alloc();
		double ret = run(env);
		env.getVariablePool().release();
		return new Literal(ret);
	}
	
	protected abstract double run(Environment env);
}
