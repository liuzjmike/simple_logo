package model.executable.math;

import model.executable.Command;
import model.executable.Executable;
import model.executable.Literal;

public abstract class MathCommand implements Command {

	public MathCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Literal eval(Executable...exec) throws Exception {
		if(exec.length != 2) {
			throw new Exception();
		}
		return concreteEval(exec);
	}
	
	@Override
	public int numParams() {
		return 2;
	}
	
	protected abstract Literal concreteEval(Executable...exec) throws Exception;
}
