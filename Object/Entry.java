/**
 * Created by lizmiller on 5/12/16.
 */
public class Entry {

    private int cardNumber;
    private String filePath;
    private int contest;
    private String entry;

    public Entry(int cardNumber, String filePath, int contest, String entry) {
        this.cardNumber = cardNumber;
        this.filePath = filePath;
        this.contest = contest;
        this.entry = entry;
    }

    public int getContest() {
        return contest;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getEntry() {
        return entry;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "["+cardNumber + ", " + contest + ", " + entry + ", " + filePath+"]";
    }

}
