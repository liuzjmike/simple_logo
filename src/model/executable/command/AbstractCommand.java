package model.executable.command;

import java.util.ArrayList;
import java.util.List;

import model.Environment;
import model.executable.Executable;
import model.executable.Literal;
import util.SLogoException;

public abstract class AbstractCommand implements Command {

    private List<Executable> myParams;
    private int numParams, offset;
    
    public AbstractCommand(int numParams) {
        myParams = new ArrayList<>();
        this.numParams = numParams;
        offset = 0;
    }
    
    @Override
    public Literal execute(Environment env) {
        //TODO: Unlimited Parameters
    	checkParamsLength();
    	checkParamsGrouping();
        double ret = 0;
        offset = 0;
        do {
        	ret = run(env);
        	offset += numParams();
        } while(offset < myParams.size());
        return new Literal(ret);
    }
    
    @Override
    public Command copy() {
        Command ret = newInstance();
    	myParams.forEach(exec -> ret.addParam(exec.copy()));
    	return ret;
    }
    
    @Override
    public Command newInstance() {
        try {
            return getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SLogoException(SLogoException.INSTANTIATION_ERROR);
        }
    }
    
    @Override
    public int numParams() {
    	return numParams;
    }
    
    @Override
    public void addParam(Executable exec) {
        myParams.add(exec);
    }
    
    @Override
    public void clearParams() {
    	myParams.clear();
    }
    
    protected void checkParams() {
    	checkParamsLength();
    	checkParamsGrouping();
    }
    
    protected void checkParamsLength() {
    	if(myParams.size() < numParams) {
            throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
        }
    }
    
    protected void checkParamsGrouping() {
    	if(numParams() != 0 && myParams.size() % numParams() != 0) {
    		throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
    	}
    }

    protected List<Executable> getParams() {
    	return myParams;
    }
    
    protected Executable getParam(int index) {
    	return myParams.get(index+offset);
    }
    
    protected double getParamValue(Environment env, int index) {
        return getParam(index).execute(env).getValue();
    }
    
    protected abstract double run(Environment env);
}
