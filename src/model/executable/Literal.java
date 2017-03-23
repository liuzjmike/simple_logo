package model.executable;

import model.Environment;

/**
 * Represents a constant double value.
 * @author Mike Liu
 *
 */
public class Literal implements Executable {
	
	double myVal;

	public Literal(double value) {
		myVal = value;
	}

	@Override
	public Literal execute(Environment env) {
		return this;
	}

	@Override
	public Literal copy() {
		return this;
	}
	
	public double getValue() {
		return myVal;
	}
}
