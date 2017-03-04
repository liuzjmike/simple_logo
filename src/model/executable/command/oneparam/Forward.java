package model.executable.command.oneparam;

import model.Environment;
import model.executable.Literal;

/**
 * Moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 * @author zhuangbihan
 *
 */
public class Forward extends OneParamCommand {

	@Override
	protected Literal concreteExecute(Environment env) {
		new TurtleOperation.operate(env, turtle -> turle.move());
		return new Literal(env.getTurtlePool().moveTurtle(getParamValue(0, env)));
	}

}
