package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

public class Quotient extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		if (getParamValue(1, env) == 0) {
			// TODO
		}
		return null;
	}

}
