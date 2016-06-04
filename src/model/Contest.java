package model;

/**
 * @author liz
 *
 * This is one contest object
 */
public class Contest {
    /** Unique Contest number*/
    private int contestNumber;
    /** Name of the contest*/
    private String name;
    /** What is the contest about*/
    private String description;
    /** The start date for the contest*/
    private String startDate;
    /** The end date for the contest*/
    private String endDate;

    /** Initializes the contest object
     * @param contestNumber - unique card number
     * @param name - contest name
     * @param description - what is the contest about
     * @param startDate - when the contest opens
     * @param endDate - when the contest closes*/
    public Contest(int contestNumber,String name, String description, String startDate, String endDate) {
        this.contestNumber = contestNumber;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the unique contest number
     *
     * @return the number
     * */
    public int getContestNumber() {
        return contestNumber;
    }

    /**
     * Returns the name of the contest
     *
     * @return the name
     * */
    public String getName() {
        return name;
    }

    /**
     * Returns the description
     *
     * @return the description as string
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the start date of the contest
     *
     * @return string for start date
     * */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns end date of contest
     *
     * @return the end date
     * */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Returns string of all the fields
     *
     * @return string
     * */
    public String toString() {
        return  contestNumber + "," + name + "," + description + "," + startDate + "," + endDate;
    }
}
