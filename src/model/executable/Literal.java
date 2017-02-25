package model.executable;

public class Literal implements Executable {
	
	double myVal;

	public Literal(double value) {
		myVal = value;
	}

	@Override
	public Literal execute() throws Exception {
		return this;
	}
	
	public double getValue() {
		return myVal;
	}
}
