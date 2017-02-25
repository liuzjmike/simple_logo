package model.executable;

import model.Environment;

public class Variable implements Executable {
	
	String myName;

	public Variable(String name) {
		myName = name;
	}

	@Override
	public Literal execute(Environment env) throws Exception {
		return env.getVariable(myName);
	}

}
