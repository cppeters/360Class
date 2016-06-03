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

    @Override
    public boolean equals(Object theObject) {
        boolean result = false;
        if(theObject == null) result = false;
        final Entry theOther = (Entry) theObject;
        if (this.getClass().equals(theObject.getClass())  &&
                (this.entryName.equals(theOther.entryName) &&
                (this.cardNumber == theOther.cardNumber) &&
                (this.contest) == theOther.contest) &&
                (this.filePath.equals(theOther.filePath)) &&
                (this.entryNumber == theOther.entryNumber) &&
                (this.toString().equals(theOther.toString())))
            result = true;
        return result;
    }

    @Override
    public int hashCode(){
        int result = 0;
        result = (int) this.entryNumber / 11;
        return result;
    }

}
