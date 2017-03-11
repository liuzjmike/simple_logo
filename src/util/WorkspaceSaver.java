package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.Workspace;

public class WorkspaceSaver {

	/**
	 * @return leaders as an ArrayList
	 * @throws ClassNotFoundException 
	 */
	public Workspace loadWorkspace(String srcPath) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(srcPath);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Workspace result = (Workspace) ois.readObject();
		ois.close();
		return result;
	}

	/**
	 * save the workspace data to file
	 * @throws IOException 
	 */
	public void store(Workspace workspace,
			String srcPath) throws IOException {
		FileOutputStream fout = new FileOutputStream(srcPath);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(workspace);
		oos.close();
	}
}
