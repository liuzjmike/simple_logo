package model.executable.command.threeparam;

import model.executable.command.AbstractCommand;

public abstract class ThreeParamCommand extends AbstractCommand {

	@Override
	public int numParams() {
		return 3;
	}
}
