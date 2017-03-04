package model.executable;

import model.Environment;

public class Variable implements Executable {
	
	String myName;

	public Variable(String name) {
		myName = name;
	}
	
	public String getName() {
		return myName;
	}

	@Override
	public Literal execute(Environment env) {
		return env.getVariablePool().get(myName);
	}

	@Override
	public void reset() {
		
	}

}
