package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liz
 */
public class User {

    /** Name of the user*/
    private String name;

    /** Card number of the user*/
    private int cardNumber;

    /** Age of the user*/
    private int age;

    /** The password of user*/
    private String loginCredential;

    /** Who is this user*/
    private String type;

    /** What entries has the user submitted to*/
    private List<Entry> entries;

    /** Constructor()
     * @param cardNumber - card number of the user
     * @param name - name of the user
     * @param age - age of the user
     * @param loginCredential - password
     * @param type - who is the user*/
    public User(int cardNumber, String name,int age, String loginCredential, String type) {
        this.age = age;
        this.cardNumber = cardNumber;
        this.name = name;
        this.loginCredential = loginCredential;
        this.type = type;
        entries = new ArrayList<>();

    }

    /** What is the user type?
     * @return the users type*/
    public String getType() {
        return type;
    }

    /** What is the card number?
     * @return the users card number*/
    public int getCardNumber() {
        return cardNumber;
    }

    /** What is the age?
     * @return the the users age*/
    public int getAge () {
        return  age;
    }

    /** What is the users name?
     * @return the users name*/
    public String getName() {
        return name;
    }

    /** What is the users password?
     * @return the users password*/
    public String getLoginCredential() {
        return loginCredential;
    }

    /** Adding a new entry that this user has created
     * @param entry - that entry that will be submitted
     * @param entryDatabase - that database that will need to have the new entry
     *
     * @exception Exception*/
    //When a new entry is added
    public void addEntry(Entry entry, EntryDatabaseManager entryDatabase) throws Exception{

        //adds the entry to the actual db entry list
        entryDatabase.addEntry(entry);
        entries.add(entry);
    }

    /**
     * @author Casey
     *
     * @param theOldEntryIdx
     * @param theNewEntry
     * @param theEntryDatabase
     * @throws Exception
     */
    public void updateEntry(int theOldEntryIdx, Entry theNewEntry, 
    		EntryDatabaseManager theEntryDatabase) throws Exception {
    	
    	theEntryDatabase.updateEntry(entries.get(theOldEntryIdx), theNewEntry);
    	entries.set(theOldEntryIdx, theNewEntry);		
    }


    /** Only used when the program is first ran
     * Used to add the entries that have been read in from the database
     * @param entry - entry that will be added*/
    //ONLY USE WHEN THE USERS ARE READ IN
    //DO NOT USE TO ADD NEW ENTRIES
    public void addReadEntries(Entry entry){

        //adds the entry to the actual db entry list
        entries.add(entry);
    }

    /** What entries has this user submitted?
     * @return all the entries that this user has submitted*/
    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return  "["+name + ", " + cardNumber + ", " + age + ", " + loginCredential + ", " + type+"]" ;
    }
}
