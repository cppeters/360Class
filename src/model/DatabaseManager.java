/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Casey
 *
 */
public abstract class DatabaseManager {
	private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int ITEM_NUMBER_IDX = 0;
    private static final int ITEM_NAME_IDX = 1;
    private static final int HEADER_SIZE = 5;
    private final String myFileName;
    private List<?> myItems;
    private Map<Integer,Contest> myMap;
    private int myCounter;


    public DatabaseManager(String theFileName)  {
        myItems = new ArrayList<>();
        myFileName = theFileName;
        myMap = new HashMap<>();
        myCounter = 0;
    }

    
    public boolean add() {
		return false;
    }
    
    public void update() {
    	
    }
    
    public List<?> getAll() {
        return myItems;
    }
    
    public int getCount() {
    	return myCounter;
    }

    public Map<Integer,?> getMap() {
        return myMap;
    }

    public void readCsvFile() {
       
    }

    public void writeCsvFile() {
    	
    }

}
