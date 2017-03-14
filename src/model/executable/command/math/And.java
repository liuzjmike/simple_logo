package model.executable.command.math;

import java.util.List;

import model.Environment;
import model.executable.Executable;
import model.executable.command.MathCommand;

/**
 * Returns 1 if test1 and test2 are non-zero, otherwise 0
 * 
 * @author zhuangbihan
 *
 */
public class And extends MathCommand {

	public And() {
		super(2);
	}

	@Override
	protected double run(Environment env, List<Executable> params) {
		boolean result = params.get(0).execute(env).getValue()!=0 && 
				         params.get(1).execute(env).getValue()!=0;
		for(int i=2; i < params.size(); i++) {
			result = result && params.get(i).execute(env).getValue()!=0;
		}
		return result ? 1:0;
	}
}
