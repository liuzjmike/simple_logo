package model.executable;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import model.executable.math.Sum;

public class Interpreter {

    public List<Node> parse(String commands) throws Exception {
    	return null;
    }
    
    private List<Node> parse(Deque<String> expressions) {
    	List<Node> roots = new ArrayList<>();
    	while(!expressions.isEmpty()) {
    		String exp = expressions.pop();
    		if(!isCommand(exp)) {
    			roots.add(new Node(new Variable()));
    		} else {
    			Command command = new Sum();
    			Node node = new Node(command);
    			for(int i = 0; i < command.numParams(); i++) {
    				node.addChildren(parse(expressions));
    			}
    			roots.add(node);
    		}
    	}
    	return roots;
    }
    
    private boolean isCommand(String exp) {
    	return true;
    }
}
