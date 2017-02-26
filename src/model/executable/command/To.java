package model.executable.command;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.Literal;

public class To extends AbstractCommand {
    
    private String myName;

	public To(String name) {
	    myName = name;
	}

	@Override
	public int numParams() {
		return 2;
	}

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		Command toAdd = new CustomizedCommand((ExecutableList)To.this.getParam(0), (ExecutableList)To.this.getParam(1));
		env.getCommandPool().add(myName, toAdd);
		return new Literal(1);
	}

}
