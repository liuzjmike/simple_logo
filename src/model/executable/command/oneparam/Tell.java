package model.executable.command.oneparam;

import java.util.ArrayList;
import java.util.List;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.command.AbstractCommand;

public class Tell extends AbstractCommand {

	public Tell() {
		super(1);
	}

	@Override
	protected double concreteExecute(Environment env) {
		List<Integer> ret = new ArrayList<>();
		((ExecutableList)getParam(0)).forEach(exec -> ret.add((int) exec.execute(env).getValue()));
		env.getTurtlePool().tell(ret);
		return ret.isEmpty() ? 0 : ret.get(ret.size()-1);
	}

}
