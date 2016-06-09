package model;

/**
 * @author liz
 */
public class EntryDatabaseManager extends DatabaseManager<Entry> {

    // EntryDatabaseManager is type 2
    /** The type for the database*/
    private final static int DATABASE_TYPE = 2;

    /** Constructor()
     *
     * Precondition: The File name should be a valid string for a file path.
     *
     * @param theFileName - the path of the file*/
    public EntryDatabaseManager(String theFileName) throws Exception {
        super(theFileName);

    }

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
     *
     * Precondition: The entry must not be null.
     *
     * @param entry - new entry that will be added*/
    public void addEntry(Entry entry) throws Exception {
        super.getMap().put(entry.getEntryNumber(), entry);
        super.getAllItems().add(entry);
        writeCsvFile();
    }

    /** Updates entry that is already in the database
     *
     * Precondition: The theOldEntry and theNewEntry must not be null.
     *
     * @param theNewEntry - the replacement entry
     * @param theOldEntry - the entry that will be replaced*/
    public void updateEntry(Entry theOldEntry, Entry theNewEntry) throws Exception {

        super.getMap().replace(theOldEntry.getEntryNumber(), theNewEntry);

        // Update the List
        super.getAllItems().set(super.getAllItems().indexOf(theOldEntry), theNewEntry);

        // Save changes
        writeCsvFile();
    }
}