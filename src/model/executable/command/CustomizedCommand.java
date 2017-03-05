package model.executable.command;

import model.Environment;
import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;

public class CustomizedCommand extends AbstractCommand {
	
	private ExecutableList varParams, myBody;

	public CustomizedCommand(ExecutableList params, ExecutableList body) {
		super(params.size());
		varParams = params;
		myBody = body;
	}
	
	public CustomizedCommand copy() {
	    return new CustomizedCommand(varParams, myBody);
	}

	@Override
	protected double concreteExecute(Environment env) {
	    env.getVariablePool().alloc();
		for(int i = 0; i < varParams.size(); i++) {
			Variable var = (Variable)varParams.get(i);
			env.getVariablePool().add(var.getName(), getParam(i).execute(env));
		}
		Literal ret = new Literal(0);
		for(Executable exec : myBody) {
            exec.reset();
			ret = exec.execute(env);
			exec.reset();
		}
		env.getVariablePool().release();
		return ret.getValue();
	}

}
