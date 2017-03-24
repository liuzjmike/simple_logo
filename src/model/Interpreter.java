package model;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import model.executable.Executable;
import model.executable.ExecutableList;
import model.executable.Literal;
import model.executable.Variable;
import model.executable.command.Command;
import model.executable.command.customize.To;
import model.executable.command.math.Sum;
import util.RegexParser;
import util.SLogoException;

/**
 * Interprets user input.
 * @author Mike Liu
 * @author Bihan Zhuang
 * 
 */
public class Interpreter {
	
    public static final String DEFAULT_SYNTAX = "Syntax";
    
	public static final String NEWLINE = "Newline";
    private static final String WHITESPACE = "Whitespace";
    private static final String LIST_START = "ListStart";
    private static final String LIST_END = "ListEnd";
    private static final String GROUP_START = "GroupStart";
    private static final String GROUP_END = "GroupEnd";
    private static final String CONSTANT = "Constant";
    private static final String VARIABLE = "Variable";
    private static final String COMMAND = "Command";
    private static final String TO = "to";
	
	private RegexParser typeParser;
	
	public Interpreter() {
		typeParser = new RegexParser();
		typeParser.setPattern(DEFAULT_SYNTAX);
	}
    
	/**
	 * Parse a raw user input <code>commands</code> into an 
	 * expression tree.
	 * @param commands
	 * @param env
	 * @return the root of the expression tree.
	 */
    public Executable parse(String commands, Environment env) {
    	Deque<String> dq = new ArrayDeque<>();
    	for (String s : commands.split(typeParser.getRegex(NEWLINE))) {
    		s = s.trim();
    		if (s.startsWith("#") || s.isEmpty()) {
    			continue;
    		}
    		dq.addAll(Arrays.asList(s.split(typeParser.getRegex(WHITESPACE))));
    	}
    	ExecutableList ret = new ExecutableList();
    	while(!dq.isEmpty()) {
    	    ret.add(parse(dq, env, false));
    	}
    	return ret;
    }
    
    private Executable parse(Deque<String> expressions, Environment env, boolean record) {
        if(expressions.isEmpty()) {
        	throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
        }
        String exp = nextToken(expressions, env, record);
        if(is(exp, LIST_START)) {
            return parseList(expressions, env, record);
        }
        else if(is(exp, GROUP_START)) {
            return parseGroup(expressions, env, record);
        }
        else if(is(exp, VARIABLE)) {
            return new Variable(exp);
        } 
        else if (is(exp, CONSTANT)) {
            return new Literal(Double.parseDouble(exp));
        }
        else if(exp.toLowerCase().equals(TO)) {
            return parseTo(expressions, env);
        }
        else if(is(exp, COMMAND)) {
            return parseCommand(exp, expressions, env, record);
        }
        else {
            throw new SLogoException(SLogoException.ILLEGAL_INPUT, exp);
        }
    }

    private boolean is(String exp, String category) {
        return typeParser.getSymbol(exp).equals(category);
    }
    
    private Executable parseList(Deque<String> expressions, Environment env, boolean record) {
        ExecutableList ret = new ExecutableList();
        while(!is(expressions.peek(), LIST_END)) {
            ret.add(parse(expressions, env, record));
        }
        nextToken(expressions, env, record);
        return ret;
    }
    
    private Executable parseGroup(Deque<String> expressions, Environment env, boolean record) {
        Command command = new Sum();
        if(!is(expressions.peek(), GROUP_END)) {
            command = env.getCommandPool().getCommand(nextToken(expressions, env, record));
            while(!is(expressions.peek(), GROUP_END)) {
                command.addParam(parse(expressions, env, record));
            }
        }
        nextToken(expressions, env, record);
        return command;
    }
    
    private Executable parseTo(Deque<String> expressions, Environment env) {
        try {
            env.getLibrary().newRecord();
            env.getLibrary().append(TO);
            String name = nextToken(expressions, env, true);
            ExecutableList params = parseParam(expressions, env);
            env.getCommandPool().declare(name, params.size());
            To to = new To(name);
            to.addParam(params);
            to.addParam(parse(expressions, env, true));
            return to;
        } catch(SLogoException se) {
            env.getLibrary().dumpLast();
            throw se;
        }
    }
    
    private Executable parseCommand(String name, Deque<String> expressions, Environment env, boolean record) {
        Command command = env.getCommandPool().getCommand(name);
        for(int i = 0; i < command.numParams(); i++) {
            command.addParam(parse(expressions, env, record));
        }
        return command;
    }
    
    private ExecutableList parseParam(Deque<String> expressions, Environment env) {
        if(!is(nextToken(expressions, env, true), LIST_START)) {
            throw new SLogoException(SLogoException.MISSING_LIST);
        }
        ExecutableList params = new ExecutableList();
        String exp;
        while(!is((exp = nextToken(expressions, env, true)), LIST_END)) {
            params.add(new Variable(exp));
        }
        return params;
    }
    
    private String nextToken(Deque<String> expressions, Environment env, boolean record) {
        String token = expressions.pop();
        if(record) {
            env.getLibrary().append(token);
        }
        return token;
    }
}
