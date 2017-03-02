package model.executable.command;

import model.executable.Executable;
import model.executable.ExecutableList;

public interface Command extends Executable {
	
	public int numParams();
	
	public void addParam(ExecutableList exec) throws Exception;
	
	public void resetParams();
}
