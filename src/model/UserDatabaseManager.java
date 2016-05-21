package model;
import javax.print.attribute.IntegerSyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


/**
 * Created by lizmiller on 5/12/16.
 */
public class UserDatabaseManager {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int USER_CARD_NUMBER_IDX = 0;
    private static final int USER_NAME_IDX = 1;
    private static final int USER_AGE_IDX = 2;
    private static final int USER_LOGIN_CREDENTIAL_IDX = 3;
    private static final int USER_IS_TYPE_IDX = 4;
    private static final int HEADER_SIZE = 5;
    private static final String FILE_HEADER = "CardNumber,Name,Age,Pin,Type";
    private String fileName;
    private Map<Integer,User> userMap;
    private Map<Integer,Entry> entries;
    private EntryDatabaseManager entryDatabaseManager;

    public UserDatabaseManager(String filename, EntryDatabaseManager entries)  {
        this.fileName = filename;
        userMap = new HashMap<>();
        this.entries = entries.getEntryMap();
        entryDatabaseManager = entries;

    }

    public User checkCredientals(int cardNumber, String pin) {
        //check user map first then judge map
        User user = null;
        if(userMap.containsKey(cardNumber)) {
            User foundUser = userMap.get(cardNumber);
            if(foundUser.getLoginCredential().equals(pin)) {
                user = foundUser;
            }
        }
        return user;
    }



    public Map<Integer,User> getUserMap() {
        return userMap;
    }

    public void readCsvFile() {
        BufferedReader fileReader = null;
        Iterator it = entries.entrySet().iterator();
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            //Reading in the header
            fileReader.readLine();
            while((line = fileReader.readLine()) != null ) {
                String[] userInfo = line.split(COMMA_DELIMITER);
                if (userInfo.length > 0) {
                    User userData = new User(Integer.parseInt(userInfo[USER_CARD_NUMBER_IDX]),(userInfo[USER_NAME_IDX]),
                            Integer.parseInt(userInfo[USER_AGE_IDX]), (userInfo[USER_LOGIN_CREDENTIAL_IDX]),
                            (userInfo[USER_IS_TYPE_IDX]));


                    //checking to see if user as an entry that already exists
                    for (Map.Entry<Integer, Entry> entry : entries.entrySet()) {
                        Entry en = entry.getValue();
                        if( en.getCardNumber() == userData.getCardNumber()) {
                            userData.addReadEntries(en);
                        }
                    }
                    userMap.put(Integer.parseInt(userInfo[USER_CARD_NUMBER_IDX]), userData);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
