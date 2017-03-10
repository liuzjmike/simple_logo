package model.executable.command.structure;

import model.Environment;
import model.executable.Literal;
import model.executable.command.AbstractCommand;

/**
 * If expr is not 0, runs the trueCommands given in the first list, otherwise
 * runs the falseCommands given in the second list Returns the value of the
 * final command executed (or 0 if no commands are executed)
 * 
 * @author zhuangbihan
 *
 */
public class IfElse extends AbstractCommand {

	public IfElse() {
		super(3);
	}

	@Override
	protected double run(Environment env) {
		Literal ret = new Literal(0);
		if (getParamValue(env, 0) != 0) {
			ret = getParam(1).execute(env);
		} else {
			ret = getParam(2).execute(env);
		}
		return ret.getValue();
	}

}
