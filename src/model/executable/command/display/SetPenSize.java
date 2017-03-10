package model.executable.command.display;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * sets background color of screen to that represented by index
 * returns given index
 * @author zhuangbihan
 *
 */
public class SetPenSize extends AbstractCommand {

	public SetPenSize() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		env.getTurtlePool().applyPen(pen -> pen.setSize(getParamValue(env, 0)));
		return getParamValue(env, 0);
	}

}
