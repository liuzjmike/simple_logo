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
    	checkParams();
        double ret = 0;
        for(offset = 0; offset < myParams.size(); offset += numParams()) {
        	ret = run(env);
        }
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
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
    	if(numParams != 0 && myParams.size() % numParams() != 0) {
            throw new SLogoException(SLogoException.WRONG_NUM_PARAMS);
        }
    }
    
    protected Executable getParam(int index) {
    	return myParams.get(index+offset);
    }
    
    protected double getParamValue(Environment env, int index) {
        return getParam(index).execute(env).getValue();
    }
    
    protected abstract double run(Environment env);
}
