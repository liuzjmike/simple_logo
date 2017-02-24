package model.executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {

	Executable myExec;
	List<Node> myChildren;
	
	public Node(Executable exec) {
		myExec = exec;
		myChildren = new ArrayList<>();
	}
	
//	public Node(Node...children) {
//		this(Arrays.asList(children));
//	}
//	
//	public Node(List<Node> children) {
//		myChildren = new ArrayList<>(children);
//	}

	public List<Node> getChildren() {
		return Collections.unmodifiableList(myChildren);
	}
	
	public void addChildren(Node child) {
		myChildren.add(child);
	}
	
	public void addChildren(List<Node> children) {
		myChildren.addAll(children);
	}
}
