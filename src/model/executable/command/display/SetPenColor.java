package model.executable.command.display;

import model.Environment;
import model.executable.command.AbstractCommand;

/**
 * sets color of the pen to that represented by index
 * returns given index
 * @author zhuangbihan
 *
 */
public class SetPenColor extends AbstractCommand {

	public SetPenColor() {
		super(1);
	}

	@Override
	protected double run(Environment env) {
		env.getTurtlePool().applyPen(pen -> pen.setColor((int) getParamValue(env, 0)));
		return getParamValue(env, 0);
	}

}
