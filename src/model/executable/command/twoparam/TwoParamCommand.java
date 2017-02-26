package model.executable.command.twoparam;

import model.executable.command.AbstractCommand;

public abstract class TwoParamCommand extends AbstractCommand {
	
	@Override
	public int numParams() {
		return 2;
	}
	
}
