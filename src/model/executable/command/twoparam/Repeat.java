package model.executable.command.twoparam;

import model.Environment;
import model.executable.Literal;

/**
 * Runs command(s) given in the list the value of expr number of times
 * Returns the value of the final command executed (or 0 if no commands are executed)
 * @author zhuangbihan
 *
 */
public class Repeat extends TwoParamCommand {
	
	public static final String REPCOUNT = ":repCount";

	@Override
	protected double concreteExecute(Environment env) {
		Literal ret = new Literal(0);
		double count = getParamValue(0, env);
		for (int i=1; i<=count; i++){
			env.getVariablePool().add(":repCount", i);
			ret = getParam(1).execute(env);
		}
		return ret.getValue();
	}

}
