package model.executable.command;

import java.util.ArrayList;
import java.util.List;

import model.Environment;
import model.executable.Executable;
import model.executable.Literal;

public abstract class AbstractCommand implements Command {

    List<Executable> myParams;
    
    public AbstractCommand() {
        myParams = new ArrayList<>();
    }
    
    @Override
    public Literal execute(Environment env) throws Exception {
        if(myParams.size() != numParams()) {
            throw new Exception();
        }
        return concreteExecute(env);
    }
    
    @Override
    public void addParam(Executable exec) throws Exception {
        if(myParams.size() >= numParams()-1) {
            throw new Exception();
        }
        myParams.add(exec);
    }
    
    @Override
    public void resetParams() {
    	myParams.clear();
    }
    
    @Override
    public boolean fullParams() {
    	return myParams.size() == numParams();
    }
    
    protected Executable getParam(int index) {
    	return myParams.get(index);
    }
    
    protected double getParamValue(int index, Environment env) throws Exception {
        return myParams.get(index).execute(env).getValue();
    }
    
    protected abstract Literal concreteExecute(Environment env) throws Exception;
}
