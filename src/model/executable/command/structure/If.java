package model.executable.command.structure;

import model.Environment;
import model.executable.Literal;
import model.executable.command.AbstractCommand;

/**
 * If expr is not 0, runs the command(s) given in the list Returns the value of
 * the final command executed ( (or 0 if no commands are executed)
 * 
 * @author zhuangbihan
 *
 */
public class If extends AbstractCommand {

	public If() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		Literal ret = new Literal(0);
		if (getParamValue(env, 0) != 0) {
			ret = getParam(1).execute(env);
		}
		return ret.getValue();
	}

}
