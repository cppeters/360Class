import java.util.Map;

/**
 * Created by lizmiller on 4/29/16.
 */




public class Main {
    public static void main(String args[]) {


        /*
        * every time a new entry is added update the map and increment the counter for the id of the entry
        * when we need to display the entries to the screen us the updated map
        * ONLY WRITE TO THE FILE ONCE THE PROGRAM IS ENDED?????
        *
        * */



        //Creating the User
        String userFile = "User.csv";
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager(userFile);
        userDatabaseManager.readCsvFile();
        System.out.println("Users");
        Map<Integer, User> userMap = userDatabaseManager.getUserMap();
        System.out.println("Contestant or Admin " + userMap);
        System.out.println("Judges that are a user " + userDatabaseManager.getJudgeMap());
        System.out.println("Get the user that belongs to cardnumber 3 " + userMap.get(3));

        System.out.println("\n\n");

        String contestFile = "Contests.csv";
        ContestDatabaseManager contestDatabaseManager = new ContestDatabaseManager(contestFile);
        contestDatabaseManager.readCsvFile();
        System.out.println("Contests");
        System.out.println(contestDatabaseManager.getContestMap());

        System.out.println("\n\n");

        String entryFile = "Entries.csv";
        EntryModel entryModel = new EntryModel(entryFile);
        entryModel.readCsvFile();
        System.out.println("Entries");

        //EXAMPLE ON HOW TO ADD NOW CONTEST
//        entryModel.addEntry("PATH",1,"EntryName!!");
        System.out.println(entryModel.getEntryMap());


        //WRITING TO FILE WILL REWRITE MAP TO THE FILE
        //TO DISPLAY CONTESTS CAN USE THE MAP
        entryModel.writeCsvFile();


    }
}
