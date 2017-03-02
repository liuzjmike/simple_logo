package model.executable.command;

import model.Environment;
import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;

public class CustomizedCommand extends AbstractCommand {
	
	private ExecutableList varParams, myBody;

	public CustomizedCommand(ExecutableList params, ExecutableList body) {
		varParams = params;
		myBody = body;
	}
	
	@Override
	public int numParams() {
		return varParams.size();
	}

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
	    env.getVariablePool().allocTemp();
		for(int i = 0; i < varParams.size(); i++) {
			Variable var = (Variable)varParams.get(i);
			env.getVariablePool().addTemp(var.getName(), getParam(i).execute(env));
		}
		Literal ret = new Literal(0);
		for(Executable exec : myBody) {
			ret = exec.execute(env);
		}
		env.getVariablePool().releaseTemp();
		resetParams();
		return ret;
	}

}
