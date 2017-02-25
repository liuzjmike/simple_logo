package model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Variable;
import model.executable.command.Command;

public class Interpreter {
    
    public Executable parse(String commands, Environment env) throws Exception {
    	return parse(new ArrayDeque<String>(Arrays.asList(commands.split(" "))), env);
    }
    
    private Executable parse(Deque<String> expressions, Environment env) throws Exception {
        ExecutableList root = new ExecutableList();
    	while(!expressions.isEmpty()) {
    		String exp = expressions.pop();
    		// TODO: check expression type literal? variable? command?
    		if(!isCommand(exp)) {
    			root.add(new Variable(exp));
    		} else {
    			Command command = env.getCommand(exp);
    			for(int i = 0; i < command.numParams(); i++) {
    				command.addParam(parse(expressions, env));
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
