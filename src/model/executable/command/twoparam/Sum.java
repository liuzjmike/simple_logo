package model.executable.command.twoparam;

import model.executable.Literal;

public class Sum extends TwoParamCommand {

	public Sum() {
		// TODO Auto-generated constructor stub
	}

    @Override
    protected Literal concreteExecute() throws Exception {
        return new Literal(getParamValue(0) + getParamValue(1));
    }
}
