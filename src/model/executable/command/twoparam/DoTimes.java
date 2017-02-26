package model.executable.command.twoparam;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;

/**
 * Runs command(s) for each value specified in the range, i.e., from (1 - limit) inclusive 
 * Returns the value of the final command executed (or 0 if no commands are executed)
 * @author zhuangbihan
 *
 */
public class DoTimes extends TwoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		Literal ret = new Literal(0);
		ExecutableList list = ((ExecutableList)getParam(0));
		String varName = ((Variable)list.get(0)).getName();
		double limit = ((Literal)list.get(1)).getValue();
		env.getVariablePool().allocTemp();
		for (double i=1; i<=limit; i++){
			env.getVariablePool().addTemp(varName, new Literal(i));
			ret = getParam(1).execute(env);
		}
		env.getVariablePool().releaseTemp();
		return ret;
	}

}
