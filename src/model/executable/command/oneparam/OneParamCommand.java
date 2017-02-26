package model.executable.command.oneparam;

import model.executable.command.AbstractCommand;

public abstract class OneParamCommand extends AbstractCommand {

	@Override
	public int numParams() {
		return 1;
	}

}
