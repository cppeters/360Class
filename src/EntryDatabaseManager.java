import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by lizmiller on 5/12/16.
 */
public class EntryDatabaseManager {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int USER_CARD_NUMBER_IDX = 0;
    private static final int USER_FILEPATH_IDX = 1;
    private static final int USER_CONTEST_IDX = 2;
    private static final int USER_ENTRY_IDX = 3;
    private static final int HEADER_SIZE = 4;
    private static final String FILE_HEADER = "CardNumber,FilePath,Contest,Entry";
    private String fileName;
    private int entryCounter;
    Map<Integer,Entry> entryMap;


    public EntryDatabaseManager(String filename)  {
        entryCounter = 1;
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
                    Entry entryData = new Entry(entryCounter,(entryInfo[USER_FILEPATH_IDX]),
                            Integer.parseInt(entryInfo[USER_CONTEST_IDX]), (entryInfo[USER_ENTRY_IDX]));

                    entryMap.put(entryCounter,entryData);
                    //Increments the entries because we do not know how many have been added in the db
                    entryCounter++;

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEntry( String filePath, int contestNumber, String entryName) {
        Entry entry = new Entry(entryCounter,filePath,contestNumber,entryName);
        entryMap.put(entryCounter,entry);
        entryCounter++;
    }


    public void writeCsvFile() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            Iterator it = entryMap.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Entry entry = (Entry)pair.getValue();
                fileWriter.append(String.valueOf((entry.getCardNumber())));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getFilePath()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getContest()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getEntry()));
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
