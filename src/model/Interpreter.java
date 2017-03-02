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
	
	public static final String DEFAULT_SYNTAX = "Syntax";
	
	private RegexParser typeParser;
	
	public Interpreter() {
		typeParser = new RegexParser();
		typeParser.setPattern(DEFAULT_SYNTAX);
	}
    
    public Executable parse(String commands, Environment env) throws Exception {
    	Deque<String> dq = new ArrayDeque<>();
    	for (String s : commands.split("\\n")) {
    		s.trim();
    		if (s.startsWith(typeParser.getSymbol("Comment")) || s.isEmpty()) {
    			continue;
    		}
    		dq.addAll(Arrays.asList(s.split("\\s+")));
    	}
    	return parse(dq, env);
    }
    
    private Executable parse(Deque<String> expressions, Environment env) throws Exception {
        ExecutableList root = new ExecutableList();
    	while(!expressions.isEmpty()) {
    		String exp = expressions.pop();
    		if(isListStart(exp)) {
    			root.add(parse(expressions, env));
    		}
    		else if(isListEnd(exp)) {
    			return root;
    		}
    		else if(isVariable(exp)) {
    			root.add(new Variable(exp));
    		} 
    		else if (isConstant(exp)) {
    			root.add(new Literal(Double.parseDouble(exp)));
    		}
    		else if (isCommand(exp)) {
    			System.out.println(exp);
    			Command command;
    			if (isTO(exp)){
    				command = new To(expressions.pop());
    			} else {
    				command = env.getCommandPool().getCommand(exp);
    			}
    			for(int i = 0; i < command.numParams(); i++) {
    				command.addParam(parse(expressions, env));
    			}
    			if (!command.fullParams()){
    				throw new RuntimeException();
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
    	System.out.println(exp);
    	return typeParser.getSymbol(exp).equals("Command");
    }
    
    private boolean isVariable(String exp) {
    	return typeParser.getSymbol(exp).equals("Variable");
    }
    
    private boolean isConstant(String exp) {
    	return typeParser.getSymbol(exp).equals("Constant");
    }
    
    private boolean isTO(String exp) {
    	return exp.toLowerCase().equals("to");
    }
    
    private boolean isListStart(String exp) {
    	return typeParser.getSymbol(exp).equals("ListStart");
    }
    
    private boolean isListEnd(String exp) {
    	return typeParser.getSymbol(exp).equals("ListEnd");
    }
}
