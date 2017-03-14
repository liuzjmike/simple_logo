package model.executable.command;

import model.Environment;

public class Definition extends ActionCommand {
    
    private String myName;
    
    public Definition(String name, int numParams) {
        super(numParams);
        myName = name;
    }
    
    @Override
    public Definition newInstance() {
        return new Definition(myName, numParams());
    }

    @Override
    protected double run(Environment env) {
        Command command = env.getCommandPool().getCommand(myName);
        for(int i = 0; i < numParams(); i++) {
            command.addParam(getParam(i));
        }
        return command.execute(env).getValue();
    }

}
