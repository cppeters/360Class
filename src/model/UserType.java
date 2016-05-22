package model;

/**
 * Created by lizmiller on 5/21/16.
 */
public class UserType {

    public enum userTypeValue {
        JUDGE, CONTESTANT, ADMIN
    }


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
