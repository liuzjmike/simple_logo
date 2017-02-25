package model.executable.command.noparam;

import model.executable.command.AbstractCommand;

public abstract class NoParamCommand extends AbstractCommand {

	public int numParams() {
		return 0;
	}

}
