package model;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lizmiller on 5/12/16.
 */
public class EntryDatabaseManager {//extends DatabaseManager<Entry>{
	
	/* Liz after extending the class this commented block is all that would be needed 
	 * I already tested for the most part.
	 * And even the last 3 get methods are pure wrappers that could be removed and I'll update
	 * any calls that it would affect. 
	 
	
	// EntryDatabaseManager is type 2
	private final static int DATABASE_TYPE = 2;

	public EntryDatabaseManager(String theFileName) {
		super(theFileName);
		
	}	
	
	@SuppressWarnings("unchecked")
	public void readCsvFile() {
		super.readCsvFile(DATABASE_TYPE, null);
	}
	
	public void writeCsvFile() throws FileNotFoundException {
		super.writeCsvFile(DATABASE_TYPE);
	}
	
	public void addEntry( Entry entry) throws FileNotFoundException {
        super.updateCount();
        super.getMap().put(super.getCount(),entry);
        writeCsvFile();
    }
	
	public void updateEntry(Entry theOldEntry, Entry theNewEntry) throws FileNotFoundException {
		for (int i : super.getMap().keySet()){
			if (super.getMap().get(i).equals(theOldEntry)){
				super.getMap().replace(i, theNewEntry);
			}
		}
		writeCsvFile();
	}

    public int getTotalEntries() {
        return super.getCount();
    }
    
    public List<Entry> getAllEntries() {
        return super.getAll();
    }

    public Map<Integer,Entry> getEntryMap() {
        return super.getMap();
    }*/

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int USER_ENTRY_NUMBER_IDX = 0;
    private static final int USER_USER_CARD_NUMBER_IDX = 1;
    private static final int USER_FILE_PATH_IDX = 2;
    private static final int USER_ENTRY_NAME_IDX = 3;
    private static final int USER_CONTEST_NUMBER_IDX = 4;
    private static final int HEADER_SIZE = 5;
    private static final String FILE_HEADER = "EntryNumber,UserCardNumber,FilePath,EntryName,Contest";
    private String fileName;
    private List<Entry> entries;
    private int entryCounter;
    Map<Integer,Entry> entryMap;
    
    // Get entries
    public List<Entry> getAllEntries() {
        return entries;
    }
    
    /**
     * @author Lan
     * @param theContestNumber
     */
    public List<Entry> getEntries(int theContestNumber) {
    	List<Entry>subListEntriess = new ArrayList<>();
    	for(int index = 0; index < entries.size(); index++){
    		if(entries.get(index).getContest() == theContestNumber){
    			subListEntriess.add(entries.get(index));
    		}
    	} 	 
        return subListEntriess;
    }
    
//    public int getTotalEntriesFromContest(int theContestNumber) {
//    	List<Entry>subListEntriess = new ArrayList<>();
//    	for(int index = 0; index < entries.size(); index++){
//    		if(entries.get(index).getContest() == theContestNumber){
//    			subListEntriess.add(entries.get(index));
//    		}
//    	} 	 
//        return subListEntriess.size();
//    }

    public EntryDatabaseManager(String filename)  {
        entryCounter = 0;
        entries = new ArrayList<>();
        this.fileName = filename;
        entryMap = new HashMap<>();
    }

    public Map<Integer,Entry> getEntryMap() {
        return entryMap;
    }

    public void readCsvFile() {
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            //Reading in the header
            fileReader.readLine();
            while((line = fileReader.readLine()) != null ) {
                String[] entryInfo = line.split(COMMA_DELIMITER);
                if (entryInfo.length > 0) {
                    Entry entryData = new Entry(Integer.parseInt(entryInfo[USER_ENTRY_NUMBER_IDX]),Integer.parseInt(entryInfo[USER_USER_CARD_NUMBER_IDX]),(entryInfo[USER_FILE_PATH_IDX]),
                            Integer.parseInt(entryInfo[USER_CONTEST_NUMBER_IDX]), (entryInfo[USER_ENTRY_NAME_IDX]));

                    entryMap.put(Integer.parseInt(entryInfo[USER_ENTRY_NUMBER_IDX]),entryData);
                    entries.add(entryData);
                    //Increments the entries because we do not know how many have been added in the db
                    entryCounter++;

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEntry( Entry entry) {
        entryCounter++;
        entryMap.put(entryCounter,entry);
        writeCsvFile();

    }
    
    /**
     * @author Casey
     * @param theOldEntry
     * @param theNewEntry
     */
    public void updateEntry(Entry theOldEntry, Entry theNewEntry) {
		for (int i : entryMap.keySet()){
			if (entryMap.get(i).equals(theOldEntry)){
				entryMap.replace(i, theNewEntry);
			}
		}
		writeCsvFile();
	}

    public int getTotalEntries() {
        return entryCounter;
    }

    public void updateEntryCount() {
        entryCounter++;
    }


    public void writeCsvFile() {
        //EntryNumber,UserCardNumber,FilePath,EntryName,Contest
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            Iterator it = entryMap.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Entry entry = (Entry)pair.getValue();
                fileWriter.append(String.valueOf((entry.getEntryNumber())));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf((entry.getCardNumber())));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getFilePath()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getEntryName()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getContest()));
                fileWriter.append(NEW_LINE_SEPARATOR);

            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
