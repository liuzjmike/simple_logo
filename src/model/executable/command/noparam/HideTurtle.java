package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Makes turtle invisible
 * Returns 0
 * @author zhuangbihan
 *
 */
public class HideTurtle extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		env.getPool().setVisible(false);
		return new Literal(0);
	}

}
