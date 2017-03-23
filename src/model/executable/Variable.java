package model.executable;

import model.Environment;

/**
 * Represents a variable.
 * @author Mike Liu
 *
 */
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
	public Variable copy() {
		return this;
	}

}
