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
    	for (String s : commands.split(typeParser.getRegex("Newline"))) {
    		s.trim();
    		if (is(s, "Comment") || s.isEmpty()) {
    			continue;
    		}
    		dq.addAll(Arrays.asList(s.split(typeParser.getRegex("Whitespace"))));
    	}
    	return parse(dq, env);
    }
    
    private ExecutableList parse(Deque<String> expressions, Environment env) throws Exception {
        ExecutableList root = new ExecutableList();
    	while(!expressions.isEmpty()) {
    		String exp = expressions.pop();
    		if(is(exp, "ListStart")) {
    			return parse(expressions, env);
    		}
    		else if(is(exp, "ListEnd")) {
    			return root;
    		}
    		else if(is(exp, "Variable")) {
    			root.add(new Variable(exp));
    			return root;
    		} 
    		else if (is(exp, "Constant")) {
    			root.add(new Literal(Double.parseDouble(exp)));
    			return root;
    		}
    		else if(exp.toLowerCase().equals("to")) {
    		    To to = new To(expressions.pop());
    		    to.addParam(parseParam(expressions));
    		    to.addParam(parse(expressions, env));
    		    root.add(to);
    		}
    		else if(is(exp, "Command")) {
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
    	if(root.isEmpty()) {
    	    throw new Exception();
    	}
    	return root;
    }

    private boolean is(String exp, String category) {
        return typeParser.getSymbol(exp).equals(category);
    }
    
    private ExecutableList parseParam(Deque<String> expressions) throws Exception {
        if(!is(expressions.pop(), "ListStart")) {
            throw new Exception();
        }
        ExecutableList params = new ExecutableList();
        String exp;
        while(!is((exp = expressions.pop()), "ListEnd")) {
            params.add(new Variable(exp));
        }
        return params;
    }
}
