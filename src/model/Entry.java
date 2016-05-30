package model;
/**
 * Created by lizmiller on 5/12/16.
 */
public class Entry {

    private int cardNumber;
    private String filePath;
    private int contest;
    private String entryName;
    private int entryNumber;

    public Entry(int entry,int cardNumber, String filePath, int contest, String entryName) {
        this.cardNumber = cardNumber;
        this.filePath = filePath;
        this.contest = contest;
        this.entryName = entryName;
        this.entryNumber = entry;

    }

    public int getContest() {
        return contest;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getEntryName() {
        return entryName;
    }

    public String getFilePath() {
        return filePath;
    }
    public int getEntryNumber() {
        return entryNumber;
    }

    @Override
    public String toString() {
        return entryNumber + "," + cardNumber + "," + filePath + "," + entryName +
        		"," + contest;
    }

}
