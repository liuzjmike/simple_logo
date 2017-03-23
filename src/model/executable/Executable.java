package model.executable;

import model.Environment;

/**
 * A class can implement the Executable class when it
 * needs to be executed in the IDE.
 * @author Mike Liu
 *
 */
public interface Executable {

	/**
	 * Executes the Executable.
	 * @param env
	 * @return Literal as the result of the execution. 
	 */
	public Literal execute(Environment env);
	
	/**
	 * Makes a deep copy of the Executable.
	 * @return
	 */
	public Executable copy();
}
