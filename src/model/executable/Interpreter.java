package model.executable;

import java.util.Deque;

import model.executable.command.Command;
import model.executable.command.twoparam.Sum;

public class Interpreter {

    public Executable parse(String commands) throws Exception {
    	return null;
    }
    
    private Executable parse(Deque<String> expressions) throws Exception {
        ExecutableList root = new ExecutableList();
    	while(!expressions.isEmpty()) {
    		String exp = expressions.pop();
    		if(!isCommand(exp)) {
    			root.add(new Variable());
    		} else {
    			Command command = new Sum();
    			for(int i = 0; i < command.numParams(); i++) {
    				command.addParam(parse(expressions));
    			}
    			root.add(command);
    		}
    	}
    	return root;
    }
    
    private boolean isCommand(String exp) {
    	return true;
    }
}
