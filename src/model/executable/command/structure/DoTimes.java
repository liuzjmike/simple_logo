package model.executable.command.structure;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;
import model.executable.command.AbstractCommand;

/**
 * Runs command(s) for each value specified in the range, i.e., from (1 - limit)
 * inclusive Returns the value of the final command executed (or 0 if no
 * commands are executed)
 * 
 * @author zhuangbihan
 *
 */
public class DoTimes extends AbstractCommand {

	public DoTimes() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		Literal ret = new Literal(0);
		ExecutableList list = ((ExecutableList) getParam(0));
		String varName = ((Variable) list.get(0)).getName();
		double limit = ((Literal) list.get(1)).getValue();
		env.getVariablePool().alloc();
		for (double i = 1; i <= limit; i++) {
			env.getVariablePool().add(varName, new Literal(i));
			ret = getParam(1).execute(env);
		}
		env.getVariablePool().release();
		return ret.getValue();
	}

}
