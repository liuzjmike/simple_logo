package model.executable;

public interface Executable {

	public Literal eval(Executable...exec) throws Exception;
	
}
