package model.executable.command;

import model.executable.Executable;

public interface Command extends Executable {
	
	public int numParams();
	
	public void addParam(Executable exec) throws Exception;
}
