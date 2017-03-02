package model.executable.command.noparam;

import model.Environment;
import model.executable.Literal;

/**
 * Reports the number Pi
 * @author zhuangbihan
 *
 */
public class Pi extends NoParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		return new Literal(Math.PI);
	}

}
