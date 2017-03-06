package model.executable.command;

import model.Environment;
import model.executable.ExecutableList;

public class To extends AbstractCommand {
    
    private String myName;

	public To(String name) {
		super(2);
	    myName = name;
	}
	
	@Override
	public To newInstance() {
	    return new To(myName);
	}

	@Override
	protected double concreteExecute(Environment env) {
	    CustomizedCommand toAdd = new CustomizedCommand((ExecutableList)To.this.getParam(0),
		        (ExecutableList)To.this.getParam(1));
		env.getCommandPool().add(myName, toAdd);
		return 1;
	}

}
