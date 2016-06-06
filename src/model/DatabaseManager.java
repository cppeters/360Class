/**
 * 
 */
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liz
 * @author Casey
 * @param <T>
 *
 */
public abstract class DatabaseManager<T> {
    /** Used to write back to the file*/
	private static final String COMMA_DELIMITER = ",";

    /** Used to write back to the file*/
    private static final String NEW_LINE_SEPARATOR = "\n";

    /** Creating file for the contest*/
    private static final String CONTEST_FILE_HEADER = 
    		"ContestNumber,Name,Description,StartDate,EndDate";

    /** Creating file for the Entry*/
    private static final String ENTRY_FILE_HEADER = 
    		"EntryNumber,UserCardNumber,FilePath,EntryName,Contest";

    /** Creating file for the User*/
    private static final String USER_FILE_HEADER = 
    		"CardNumber,Name,Age,Pin,Type";

    /** Creating file for the judge*/
    private static final String JUDGE_FILE_HEADER =
            "CardNumber,Name,Age,Pin,Type,ContestNumber,FirstPlace,SecondPlace,ThirdPlace";

    /** Name of the file*/
    private final String myFileName;

    /** What is within the file*/
    private List<T> myItems;

    /** What holds the items*/
    private Map<Integer, T> myMap;                  // Map<Unique Identifier Number, Type of Object>


    /** Constructor()
     * @param theFileName - file name*/
    public DatabaseManager(String theFileName) throws Exception {
        myItems = new ArrayList<>();
        myMap = new HashMap<>();

        File theFile = new File(theFileName);
        String theFileStr = theFile.getAbsolutePath();
        String theFileType = theFileStr.substring(theFileStr.lastIndexOf('.') + 1);
        if (!theFile.exists()) throw new FileNotFoundException();
        else if (!theFileType.equals("csv")) throw new NumberFormatException();
        else myFileName = theFileName;
    }

    /** Returns all the items in the db file
     * @return myItems*/
    public List<T> getAllItems() {
        return myItems;
    }

    /** Gets the count of all the items in the in the db
     * @return the total number of items*/
    public int getItemCount() {
    	return myItems.size();
    }

    /** Gets the map
     * @return the map of the values in the db*/
    public Map<Integer, T> getMap() {
        return myMap;
    }

    @SuppressWarnings("unchecked")
    /** Reading in the file to create the list of values */
	public void readCsvFile(int theType, EntryDatabaseManager theEntryDB) throws Exception{
        try {
        	T theData = null;
        	String[] theInfo;
            String line = "";
            BufferedReader theFileReader = new BufferedReader(new FileReader(myFileName));
            //Reading in the header
            theFileReader.readLine();
            while((line = theFileReader.readLine()) != null ) {
                theInfo = line.split(COMMA_DELIMITER);
                if (theInfo.length > 0) {
                    switch (theType) {
                        // Case 1: Contest
                        case 1:
                            theData = (T) new Contest(Integer.parseInt(theInfo[0]), theInfo[1],
                                    theInfo[1], theInfo[3], theInfo[4]);

                            break;
                        // Case 2: Entry
                        case 2:
                            theData = (T) new Entry(Integer.parseInt(theInfo[0]), Integer.parseInt(theInfo[1]), (theInfo[2]),
                                    Integer.parseInt(theInfo[4]), theInfo[3]);
                            break;
                        // Case 3: User
                        case 3:
                            theData = (T) new User(Integer.parseInt(theInfo[0]), theInfo[1],
                                    Integer.parseInt(theInfo[2]), theInfo[3], theInfo[4]);

                            //checking to see if user has an entry that already exists
                            for (Map.Entry<Integer, Entry> entry : theEntryDB.getMap().entrySet()) {
                                Entry en = entry.getValue();
                                if (en.getCardNumber() == ((User) theData).getCardNumber()) {
                                    ((User) theData).addReadEntries(en);
                                }
                            }
                            break;
                        // Case 4: Judge
                        case 4:
                            theData = (T) new Judge(Integer.parseInt(theInfo[0]), theInfo[1],
                                    Integer.parseInt(theInfo[2]), theInfo[3], theInfo[4],
                                    Integer.parseInt(theInfo[5]), Integer.parseInt(theInfo[6]),
                                    Integer.parseInt(theInfo[7]), Integer.parseInt(theInfo[8]));
                        default:
                            break;
                    }
                    myMap.put(Integer.parseInt(theInfo[0]), theData);
                    myItems.add(theData);
                }
            }
            theFileReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /** Writing the new information back to the file
     * @param theType -  which file to write to*/
    public void writeCsvFile(int theType) throws Exception {

        PrintWriter pw = new PrintWriter(myFileName);
        StringBuilder sb = new StringBuilder();
        switch (theType) {
            case 1:
                sb.append(CONTEST_FILE_HEADER);
                break;
            case 2:
                sb.append(ENTRY_FILE_HEADER);
                break;
            case 3:
                sb.append(USER_FILE_HEADER);
                break;
            case 4:
                sb.append(JUDGE_FILE_HEADER);
                break;
            default:
                break;
        }
        sb.append(NEW_LINE_SEPARATOR);
        // If it is a Judge use the List for multiple occurrences
        if (theType == 4) {
            for (T myItem : myItems) {
                sb.append(myItem.toString());
                sb.append(NEW_LINE_SEPARATOR);
            }
        }
        else {
            for (int i : myMap.keySet()) {
                sb.append(myMap.get(i).toString());
                sb.append(NEW_LINE_SEPARATOR);
            }
        }
        pw.write(sb.toString());
        pw.close();
    }
}
