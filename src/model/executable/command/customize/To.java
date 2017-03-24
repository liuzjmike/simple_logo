package model.executable.command.customize;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.command.ActionCommand;
import model.executable.command.Command;

public class To extends ActionCommand {
    
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
	protected double run(Environment env) {
	    Command toAdd = new CustomizedCommand((ExecutableList)To.this.getParam(0),
		        (ExecutableList)To.this.getParam(1));
		env.getCommandPool().add(myName, toAdd);
		return 1;
	}

}
