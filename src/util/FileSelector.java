package util;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileSelector {
    
    FileChooser myChooser;
    
    public FileSelector(String extension) {
        myChooser = makeFileChooser(extension);
    }
    
    public File chooseFile(String text) {
        return chooseFile(text, null);
    }
    
    public File chooseFile(String text, Stage stage) {
        myChooser.setTitle(text);
        return myChooser.showOpenDialog(stage);
    }
    
    private FileChooser makeFileChooser(String extension) {
        FileChooser result = new FileChooser();
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extension));
        return result;
    }

}
