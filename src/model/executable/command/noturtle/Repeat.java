package model.executable.command.noturtle;

import model.Environment;
import model.executable.Literal;
import model.executable.command.ActionCommand;

/**
 * Runs command(s) given in the list the value of expr number of times Returns
 * the value of the final command executed (or 0 if no commands are executed)
 * 
 * @author zhuangbihan
 *
 */
public class Repeat extends ActionCommand {

	public Repeat() {
		super(2);
	}

	public static final String REPCOUNT = ":repCount";

	@Override
	protected double run(Environment env) {
		Literal ret = new Literal(0);
		double count = getParamValue(env, 0);
		env.getVariablePool().alloc();
		for (int i = 1; i <= count; i++) {
			env.getVariablePool().add(":repCount", i);
			ret = getParam(1).copy().execute(env);
		}
        env.getVariablePool().release();
		return ret.getValue();
	}

}
