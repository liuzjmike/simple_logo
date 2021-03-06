package model.executable.command.noturtle;

import java.util.ArrayList;
import java.util.List;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.command.ActionCommand;

/**
 * Sets turtles that will follow commands hereafter Returns last value in
 * turtles list
 * 
 * @author zhuangbihan
 *
 */
public class Tell extends ActionCommand {

	public Tell() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		List<Integer> ret = new ArrayList<>();
		((ExecutableList) getParam(0)).forEach(exec -> ret.add((int) exec.execute(env).getValue()));
		env.getTurtlePool().tell(ret);
		return ret.isEmpty() ? 0 : ret.get(ret.size() - 1);
	}

}
