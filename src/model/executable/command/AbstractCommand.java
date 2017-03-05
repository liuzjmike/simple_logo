package model.executable.command;

import java.util.ArrayList;
import java.util.List;

import model.Environment;
import model.executable.Executable;
import model.executable.Literal;
import util.SLogoException;

public abstract class AbstractCommand implements Command {

    private List<Executable> myParams;
    private int numParams;
    
    public AbstractCommand(int numParams) {
        myParams = new ArrayList<>();
        this.numParams = numParams;
    }
    
    @Override
    public Literal execute(Environment env) {
        if(myParams.size() != numParams()) {
            throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
        }
        return new Literal(concreteExecute(env));
    }
    
    @Override
    public void reset() {
    	myParams.forEach(exec -> exec.reset());
    }
    
    @Override
    public int numParams() {
    	return numParams;
    }
    
    @Override
    public void addParam(Executable exec) {
        if(myParams.size() >= numParams()) {
        	throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
        }
        myParams.add(exec);
    }
    
    @Override
    public void clearParams() {
    	myParams.clear();
    }
    
    protected Executable getParam(int index) {
    	return myParams.get(index);
    }
    
    protected double getParamValue(int index, Environment env) {
        return myParams.get(index).execute(env).getValue();
    }
    
    protected abstract double concreteExecute(Environment env);
}
