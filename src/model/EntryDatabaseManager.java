package model;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * @author liz
 */
public class EntryDatabaseManager extends DatabaseManager<Entry> {

    // EntryDatabaseManager is type 2
    /** The type for the database*/
    private final static int DATABASE_TYPE = 2;

    /** Constructor()
     * @param theFileName - the path of the file*/
    public EntryDatabaseManager(String theFileName) throws Exception {
        super(theFileName);

    }

    @SuppressWarnings("unchecked")
    public void readCsvFile() throws Exception {
        super.readCsvFile(DATABASE_TYPE, null);
    }

    /**
     * Writes the the CSV file
     * @exception Exception*/
    public void writeCsvFile() throws Exception {
        super.writeCsvFile(DATABASE_TYPE);
    }

    /** Adds a new entry to the database
     * @param entry - new entry that will be added*/
    public void addEntry(Entry entry) throws Exception {
        super.getMap().put(entry.getEntryNumber(), entry);
        super.getAllItems().add(entry);
        writeCsvFile();
    }

    /** Updates entry that is already in the database
     * @param theNewEntry - the replacement entry
     * @param theOldEntry - the entry that will be replaced*/
    public void updateEntry(Entry theOldEntry, Entry theNewEntry) throws Exception {
        // Update the Map
       /* for (int i : super.getMap().keySet()) {
            if (super.getMap().get(i).equals(theOldEntry)) {
                super.getMap().replace(i, theNewEntry);
            }
        }*/
        super.getMap().replace(theOldEntry.getEntryNumber(), theNewEntry);

        // Update the List
        super.getAllItems().set(super.getAllItems().indexOf(theOldEntry), theNewEntry);

        // Save changes
        writeCsvFile();
    }
}