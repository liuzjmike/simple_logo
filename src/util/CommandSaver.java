package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import model.executable.command.Command;

public class CommandSaver {

	/**
	 * @return leaders as an ArrayList
	 * @throws ClassNotFoundException 
	 */
	public Map<String, Command> loadCommands(String srcPath) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(srcPath);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Map<String, Command> result = (Map<String, Command>) ois.readObject();
		ois.close();
		return result;
	}

	/**
	 * save the workspace data to file
	 * @throws IOException 
	 */
	public void store(Map<String, Command> commandMap, String srcPath) throws IOException {
		FileOutputStream fout = new FileOutputStream(srcPath);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(commandMap);
		oos.close();
	}
}
