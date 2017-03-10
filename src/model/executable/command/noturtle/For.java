package model.executable.command.noturtle;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;
import model.executable.command.AbstractCommand;

/**
 * Runs command(s) for each value specified in the range, i.e., from (start -
 * end), going by increment Returns the value of the final command executed (or
 * 0 if no commands are executed)
 * 
 * @author zhuangbihan
 *
 */
public class For extends AbstractCommand {

	public For() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		Literal ret = new Literal(0);
		ExecutableList list = ((ExecutableList) getParam(0));
		String varName = ((Variable) list.get(0)).getName();
		double start = ((Literal) list.get(1)).getValue();
		double end = ((Literal) list.get(2)).getValue();
		double incre = ((Literal) list.get(3)).getValue();
		env.getVariablePool().alloc();
		for (double i = start; i <= end; i += incre) {
			env.getVariablePool().add(varName, new Literal(i));
			ret = getParam(1).execute(env);
		}
		env.getVariablePool().release();
		return ret.getValue();
	}

}
