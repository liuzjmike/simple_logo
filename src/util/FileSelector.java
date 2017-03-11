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
    
    public File open() {
        return open(null);
    }
    
    public File open(Stage stage) {
        myChooser.setTitle("Choose file");
        return myChooser.showOpenDialog(stage);
    }
    
    public File saveTo(Stage stage) {
        myChooser.setTitle("Save to");
        return myChooser.showSaveDialog(stage);
    }
    
    private FileChooser makeFileChooser(String extension) {
        FileChooser result = new FileChooser();
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extension));
        return result;
    }

}
