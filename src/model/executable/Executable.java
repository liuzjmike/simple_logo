package model.executable;

import model.Environment;

public interface Executable {

	public Literal execute(Environment env);
	
	public Executable copy();
}
