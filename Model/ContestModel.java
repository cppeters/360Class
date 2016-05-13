import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizmiller on 5/12/16.
 */
public class ContestModel {

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


    public ContestModel(String filename)  {
        this.fileName = filename;
        contests = new ArrayList<>();
        contestMap = new HashMap<>();
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
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
