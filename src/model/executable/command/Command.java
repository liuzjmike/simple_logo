package model.executable.command;

import model.executable.Executable;

/**
 * Defines the functionalities of a command.
 * @author Mike Liu
 *
 */
public interface Command extends Executable {
	
	public int numParams();
	
	public void addParam(Executable exec);
	
	/**
	 * Instantiates a Command of the same type.
	 * Parameters of the original Command are not copied.
	 * @return
	 */
	public Command newInstance();
}
