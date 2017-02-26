package model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;
import model.executable.command.Command;
import model.executable.command.To;
import util.RegexParser;

public class Interpreter {
	
	public static final String COMMAND = "Command";
	public static final String VAR = "Variable";
	public static final String CONST = "Constant";
	public static final String TO = "to";
	
	private RegexParser typeParser;
	
	public Interpreter() {
		typeParser = new RegexParser();
	}
    
    public Executable parse(String commands, Environment env) throws Exception {
    	return parse(new ArrayDeque<String>(Arrays.asList(commands.trim().split(" "))), env);
    }
    
    private Executable parse(Deque<String> expressions, Environment env) throws Exception {
        ExecutableList root = new ExecutableList();
    	while(!expressions.isEmpty()) {
    		String exp = expressions.pop();
    		// TODO
    		if(isVariable(exp)) {
    			root.add(new Variable(exp));
    		} 
    		else if (isConstant(exp)) {
    			root.add(new Literal(Double.parseDouble(exp)));
    		} 
			else if (isTO(exp)) {
				To to = new To(expressions.pop());
				for(int i = 0; i < to.numParams(); i++) {
    				to.addParam(parse(expressions, env));
    			}
    			root.add(to);
			}
    		else if (isCommand(exp)) {
    			Command command = env.getCommandPool().getCommand(exp);
    			for(int i = 0; i < command.numParams(); i++) {
    				command.addParam(parse(expressions, env));
    			}
    			root.add(command);
    		} 
    		else {
    			throw new RuntimeException();
    		}
    	}
    	return root;
    }
    
    private boolean isCommand(String exp) {
    	return typeParser.getSymbol(exp).equals(COMMAND);
    }
    
    private boolean isVariable(String exp) {
    	return typeParser.getSymbol(exp).equals(VAR);
    }
    
    private boolean isConstant(String exp) {
    	return typeParser.getSymbol(exp).equals(CONST);
    }
    
    private boolean isTO(String exp) {
    	return exp.equals(TO);
    }
}
