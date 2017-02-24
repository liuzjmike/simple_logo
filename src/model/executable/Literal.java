package model.executable;

public class Literal implements Executable {
	
	double myVal;

	public Literal(double value) {
		myVal = value;
	}

	@Override
	public Literal eval(Executable... exec) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getValue() {
		return myVal;
	}
}
