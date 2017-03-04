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

	@Override
	protected double concreteExecute(Environment env) {
	    env.getVariablePool().alloc();
		for(int i = 0; i < varParams.size(); i++) {
			Variable var = (Variable)varParams.get(i);
			env.getVariablePool().add(var.getName(), getParam(i).execute(env));
		}
		Literal ret = new Literal(0);
		for(Executable exec : myBody) {
			ret = exec.execute(env);
		}
		env.getVariablePool().release();
		clearParams();
		reset();
		return ret.getValue();
	}

}
