package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

/**
 * Created by lizmiller on 5/12/16.
 */
public class ContestDatabaseManager {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int USER_CONTEST_NUMBER_IDX = 0;
    private static final int USER_NAME_IDX = 1;
    private static final int USER_DESCRIPTION_IDX = 2;
    private static final int USER_START_DATE_IDX = 3;
    private static final int USER_END_DATE_IDX = 4;
    private static final int HEADER_SIZE = 5;
    private static final String FILE_HEADER = "ContestNumber,Name,Description,StartDate,EndDate";
    private String fileName;
    private List<Contest> contests;
    private Map<Integer,Contest> contestMap;
    /**The current number of Contests in the contestMap.*/
    private int contestCounter;


    public ContestDatabaseManager(String filename)  {
        this.fileName = filename;
        contests = new ArrayList<>();
        contestMap = new HashMap<>();
        contestCounter = 0;
    }

    /**
     * Creates and adds a Contest to the database, unless there was an existing contest by the same name.
     * @author Liz, Tabi
     * @param contestName
     * @param contestDescription
     * @param startDate
     * @param endDate
     * @return true on success; false otherwise.
     */
    public boolean addContest(String contestName, String contestDescription, String startDate,
    		String endDate) {
    	// TODO add any other business rules/checks to this if statement.
    	// for example, a problem with the format of the dates, or strings are too long
    	if (contestNameIsUnique(contestName)) {
    		Contest contest = new Contest(++contestCounter, contestName, contestDescription, startDate, endDate);
            contestMap.put(contest.getContestNumber(), contest);
            contests.add(contest);
            writeCsvFile();
            return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * @author Tabi
     * @param contestName
     * @return true if there is no existing contest with the given name; false otherwise.
     */
    private boolean contestNameIsUnique(String contestName) {
    	for (Contest c : contestMap.values()) {
    		if (c.getName().equals(contestName)) {
    			return false;
    		}
    	}
    	return true;
    }


    public List<Contest> getAllContests() {
        return contests;
    }

    public Map<Integer,Contest> getContestMap() {
        return contestMap;
    }

    public void readCsvFile() {
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            //Reading in the header
            fileReader.readLine();
            while((line = fileReader.readLine()) != null ) {
                String[] contestInfo = line.split(COMMA_DELIMITER);
                if (contestInfo.length > 0) {
                    Contest contestData = new Contest(Integer.parseInt(contestInfo[USER_CONTEST_NUMBER_IDX]),(contestInfo[USER_NAME_IDX]),(contestInfo[USER_DESCRIPTION_IDX]),
                            (contestInfo[USER_START_DATE_IDX]), (contestInfo[USER_END_DATE_IDX]));

                    contestMap.put(Integer.parseInt(contestInfo[USER_CONTEST_NUMBER_IDX]), contestData);
                    contests.add(contestData);
                    contestCounter++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeCsvFile() {
        //EntryNumber,UserCardNumber,FilePath,EntryName,Contest
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            Iterator it = contestMap.entrySet().iterator();
            while(it.hasNext()) {
                //ContestNumber,Name,Description,StartDate,EndDate

                Map.Entry pair = (Map.Entry)it.next();
                Contest contest = (Contest) pair.getValue();
                fileWriter.append(String.valueOf((contest.getContestNumber())));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf((contest.getName())));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(contest.getDescription()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(contest.getStartDate()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(contest.getEndDate()));
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
