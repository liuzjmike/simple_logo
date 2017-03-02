package model.executable;

import model.Environment;

public class Literal implements Executable {
	
	double myVal;

	public Literal(double value) {
		myVal = value;
	}

	@Override
	public Literal execute(Environment env) {
		return this;
	}
	
	public double getValue() {
		return myVal;
	}
}
