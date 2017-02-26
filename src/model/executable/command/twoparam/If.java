package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * If expr is not 0, runs the command(s) given in the list
 * Returns the value of the final command executed ( (or 0 if no commands are executed)
 * @author zhuangbihan
 *
 */
public class If extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		Literal ret = new Literal(0);
		if (getParamValue(0, env) != 0){
			ret = getParam(1).execute(env);
		}
		return ret;
	}

}
