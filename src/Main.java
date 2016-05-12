import java.util.Date;

/**
 * Created by lizmiller on 4/29/16.
 */




public class Main {
    public static void main(String args[]) {


        //Creating the User
        String userFile = "User.csv";
        UserModel userModel = new UserModel(userFile);
        userModel.readCsvFile();
        System.out.println(userModel.getAllUsers().toString());


        String contestFile = "Contests.csv";
        ContestModel contestModel = new ContestModel(contestFile);
        contestModel.readCsvFile();
        System.out.println(contestModel.getAllContests().toString());


        String entryFile = "Entries.csv";
        EntryModel entryModel = new EntryModel(entryFile);
        entryModel.readCsvFile();
        entryModel.addEntry(1111,"PATH",1,"EntryName");
        entryModel.writeCsvFile();
        System.out.println(entryModel.getAllEntries().toString());


    }
}
