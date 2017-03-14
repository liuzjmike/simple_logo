package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import javafx.stage.Stage;

public class TextFileParser {

    private FileSelector mySelector;
    
    public TextFileParser(String extension) {
        mySelector = new FileSelector(extension);
    }
    
    public void save(String toSave, Stage stage) {
        File file = mySelector.saveTo(stage);
        if(file != null) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(toSave);
            } catch (IOException e) {
                throw new SLogoException(SLogoException.INVALID_FILE);
            }
        }
    }
    
    public void save(Collection<String> toSave, Stage stage) {
        save(String.join(Constants.NEWLINE, toSave), stage);
    }
    
    public String load() {
        File file = mySelector.open();
        if(file != null) {
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(Constants.NEWLINE);
                }
                return sb.toString();
            } catch (IOException e) {
                throw new SLogoException(SLogoException.INVALID_FILE);
            }
        }
        return "";
    }
}
