package model;

/**
 * @author liz
 */
public class UserType {

    /** Enum used for the type of users*/
    public enum userTypeValue {
        JUDGE, CONTESTANT, ADMIN
    }

    /** Checks to see what type the user belongs to
     * @param type - of user*/
    public static UserType.userTypeValue checkType(String type) {
        if(type.equals(userTypeValue.ADMIN.toString())) {
            return userTypeValue.ADMIN;
        } else if(type.equals(userTypeValue.CONTESTANT.toString())) {
            return userTypeValue.CONTESTANT;
        } else {
            return userTypeValue.JUDGE;
        }

    }
}
