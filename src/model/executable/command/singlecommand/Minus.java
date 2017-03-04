package model.executable.command.singlecommand;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Returns negative of the values of expr
 * 
 * @author zhuangbihan
 *
 */
public class Minus extends AbstractCommand {

	public Minus() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		return -1 * getParamValue(0, env);
	}

}
