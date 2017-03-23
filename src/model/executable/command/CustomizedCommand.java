package model.executable.command;

import model.Environment;
import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;

/**
 * Represents a user-defined command
 * @author Mike Liu
 *
 */
public class CustomizedCommand extends ActionCommand {
	
	private ExecutableList varParams, myBody;

	public CustomizedCommand(ExecutableList params, ExecutableList body) {
		super(params.size());
		varParams = params;
		myBody = body;
	}
	
	@Override
	public CustomizedCommand newInstance() {
	    return new CustomizedCommand(varParams.copy(), myBody.copy());
	}

	@Override
	protected double run(Environment env) {
		for(int i = 0; i < varParams.size(); i++) {
			Variable var = (Variable)varParams.get(i);
			env.getVariablePool().add(var.getName(), getParam(i).execute(env));
		}
		Literal ret = new Literal(0);
		for(Executable exec : myBody) {
			ret = exec.execute(env);
		}
		return ret.getValue();
	}
}
