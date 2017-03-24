package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import util.CollectionTransformer;
import util.Constants;

/**
 * Provides <code>String</code> representation of user-defined
 * commands and global variables.
 * @author Mike Liu
 * 
 */
public class Library {
    
    public static final String VARIABLE_COMMAND = "Make %s %s";

    Deque<List<String>> myLibrary;
    Supplier<Map<String, Double>> myGlobal;
    
    public Library(Supplier<Map<String, Double>> globalVars) {
        myLibrary = new ArrayDeque<>();
        myGlobal = globalVars;
    }
    
    public Library newRecord() {
        myLibrary.add(new ArrayList<>());
        return this;
    }
    
    public void append(String token) {
        myLibrary.peekLast().add(token);
    }
    
    public void appendAll(String...tokens) {
        for(int i = 0; i < tokens.length; i++) {
            append(tokens[i]);
        }
    }
    
    public void dumpLast() {
        myLibrary.remove();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                new CollectionTransformer()
                .mapToSortedList(myGlobal.get())
                .stream()                
                .map(entry -> String.format(VARIABLE_COMMAND, entry.getKey(), Double.toString(entry.getValue())))
                .collect(Collectors.joining(Constants.NEWLINE)));
        sb.append(Constants.NEWLINE + myLibrary.stream()
                                               .map(list -> list.stream()
                                                                .collect(Collectors.joining(" ")))
                                               .collect(Collectors.joining(Constants.NEWLINE)));
        return sb.toString();
    }
}
