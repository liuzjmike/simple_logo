package model.executable.command.noturtle;

import java.util.ArrayList;
import java.util.List;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.command.AbstractCommand;

/**
 * Only the turtles given in first list all run commands given in the second
 * list Returns result of last command run by the last turtle
 * 
 * @author zhuangbihan
 *
 */
public class Ask extends AbstractCommand {

	public Ask() {
		super(2);
	}

	@Override
	protected double run(Environment env) {
		List<Integer> oldActive = env.getTurtlePool().allActiveID();
		List<Integer> newActive = new ArrayList<>();
		((ExecutableList) getParam(0)).forEach(exec -> newActive.add((int) exec.execute(env).getValue()));
		env.getTurtlePool().tell(newActive);
		Literal ret = getParam(1).execute(env);
		env.getTurtlePool().tell(oldActive);
		return ret.getValue();
	}

}
