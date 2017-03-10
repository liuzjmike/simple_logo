package model.executable.command.singlecommand;

import java.util.List;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * Tell turtles matching given condition to run commands given in the second list
 * Returns result of last command run
 * @author zhuangbihan
 *
 */
public class AskWith extends AbstractCommand {

	public AskWith() {
		super(2);
	}

	@Override
	protected double concreteExecute(Environment env) {
		List<Integer> oldActive = env.getTurtlePool().allActiveID();
		env.getTurtlePool().askWith(turtle -> getParamValue(0,env) > 0);
		double ret = getParamValue(1, env);
		env.getTurtlePool().tell(oldActive);
		return ret;
	}

}
