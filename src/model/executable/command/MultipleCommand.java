package model.executable.command;

import model.Environment;
import model.executable.Literal;

public abstract class MultipleCommand extends AbstractCommand {
	
	private boolean executed;
	private Literal myValue;

	public MultipleCommand(int numParams) {
		super(numParams);
		executed = false;
	}
	
	@Override
	public Literal execute(Environment env) {
        if(!executed) {
        	myValue = super.execute(env);
        	executed = true;
        }
        return myValue;
    }
	
	@Override
	public void reset() {
		executed = false;
	}

}
