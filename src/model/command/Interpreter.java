package model.command;

public interface Interpreter {

    public Command parseCommand(String command) throws Exception;
}
