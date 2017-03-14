package model.executable.command;

import model.Environment;
import model.executable.Executable;
import model.executable.Literal;

public abstract class ActionCommand extends AbstractCommand {
	
	int offset;
    
    public ActionCommand(int numParams) {
		super(numParams);
		offset = 0;
	}

	@Override
    public Literal execute(Environment env) {
    	checkParams();
        double ret = 0;
        offset = 0;
        do {
        	ret = run(env);
        	offset += numParams();
        } while(offset < getParams().size());
        return new Literal(ret);
    }
    
    protected Executable getParam(int index) {
    	return super.getParam(index+offset);
    }
    
    protected abstract double run(Environment env);
}
