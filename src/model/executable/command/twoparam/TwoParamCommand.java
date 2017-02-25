package model.executable.command.twoparam;

import model.executable.command.AbstractCommand;

public abstract class TwoParamCommand extends AbstractCommand {

	public TwoParamCommand() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int numParams() {
		return 2;
	}
}
