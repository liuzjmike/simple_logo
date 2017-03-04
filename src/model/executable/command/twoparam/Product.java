package model.executable.command.twoparam;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns product of the values of expr1 and expr2
 * @author zhuangbihan
 *
 */
public class Product extends AbstractCommand {

	public Product() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return getParamValue(0, env) * getParamValue(1, env);
	}

}
