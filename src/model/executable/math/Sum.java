package model.executable.math;

import model.executable.Executable;
import model.executable.Literal;

public class Sum extends MathCommand {

	public Sum() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Literal concreteEval(Executable... exec) throws Exception {
		return new Literal(exec[0].eval().getValue() + exec[1].eval().getValue());
	}

}
