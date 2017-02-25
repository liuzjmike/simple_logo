package model.executable;

import model.Environment;

public interface Executable {

	public Literal execute(Environment env) throws Exception;
	
}
