package model.executable.command;

import model.Environment;
import model.executable.ExecutableList;
import model.executable.Literal;

public class To extends AbstractCommand {

	public To(String name) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int numParams() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	protected Literal concreteExecute(Environment env) throws Exception {
		Command toAdd = new CustomizedCommand((ExecutableList)To.this.getParam(0), (ExecutableList)To.this.getParam(1));
		env.addCommand(toAdd);
		return new Literal(1);
	}

}
