package model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;
import model.executable.command.Command;
import model.executable.command.CustomizedCommand;
import model.executable.command.To;
import util.RegexParser;
import util.SLogoException;

public class Interpreter {
	
	public static final String DEFAULT_SYNTAX = "Syntax";
	
	private RegexParser typeParser;
	
	public Interpreter() {
		typeParser = new RegexParser();
		typeParser.setPattern(DEFAULT_SYNTAX);
	}
    
    public Executable parse(String commands, Environment env) {
    	Deque<String> dq = new ArrayDeque<>();
    	for (String s : commands.split(typeParser.getRegex("Newline"))) {
    		s.trim();
    		if (is(s, "Comment") || s.isEmpty()) {
    			continue;
    		}
    		dq.addAll(Arrays.asList(s.split(typeParser.getRegex("Whitespace"))));
    	}
    	ExecutableList ret = new ExecutableList();
    	while(!dq.isEmpty()) {
    	    ret.add(parse(dq, env));
    	}
    	return ret;
    }
    
    private Executable parse(Deque<String> expressions, Environment env) {
        if(expressions.isEmpty()) {
        	throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
        }
        String exp = expressions.pop();
        if(is(exp, "ListStart")) {
            ExecutableList ret = new ExecutableList();
            while(!is(expressions.peek(), "ListEnd")) {
                ret.add(parse(expressions, env));
            }
            expressions.pop();
            return ret;
        }
        else if(is(exp, "Variable")) {
            return new Variable(exp);
        } 
        else if (is(exp, "Constant")) {
            return new Literal(Double.parseDouble(exp));
        }
        else if(exp.toLowerCase().equals("to")) {
            String name = expressions.pop();
            ExecutableList params = parseParam(expressions);
            env.getCommandPool().add(name, new CustomizedCommand(params, new ExecutableList()));
            To to = new To(name);
            to.addParam(params);
            to.addParam(parse(expressions, env));
            return to;
        }
        else if(is(exp, "Command")) {
            Command command = env.getCommandPool().getCommand(exp);
            for(int i = 0; i < command.numParams(); i++) {
                command.addParam(parse(expressions, env));
            }
            return command;
        }
        else {
            throw new SLogoException(SLogoException.ILLEGAL_INPUT);
        }
    }

    private boolean is(String exp, String category) {
        return typeParser.getSymbol(exp).equals(category);
    }
    
    private ExecutableList parseParam(Deque<String> expressions) {
        if(!is(expressions.pop(), "ListStart")) {
            throw new SLogoException(SLogoException.MISSING_LIST);
        }
        ExecutableList params = new ExecutableList();
        String exp;
        while(!is((exp = expressions.pop()), "ListEnd")) {
            params.add(new Variable(exp));
        }
        return params;
    }
}
