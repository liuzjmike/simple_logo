package model.executable.command.math;

import model.Environment;
import model.executable.command.ActionCommand;

/**
 * Returns product of the values of expr1 and expr2
 * 
 * @author zhuangbihan
 *
 */
public class Product extends ActionCommand {

	public Product() {
		super(2);
	}
	
	@Override
	protected double run(Environment env) {
		return getParamValue(env, 0) * getParamValue(env, 1);
	}

}
