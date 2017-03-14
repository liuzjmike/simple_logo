package model.executable.command;

import java.util.List;

import model.Environment;
import model.executable.Executable;
import model.executable.Literal;

public abstract class MathCommand extends AbstractCommand {

	public MathCommand(int numParams) {
		super(numParams);
	}
	
	@Override
	public Literal execute(Environment env) {
		checkParamsLength();
		env.getVariablePool().alloc();
		double ret = run(env, getParams());
		env.getVariablePool().release();
		return new Literal(ret);
	}
	
	protected abstract double run(Environment env, List<Executable> params);
}
