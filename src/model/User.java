package model;
/**
 * Created by lizmiller on 5/12/16.
 */
public class User {

    private String name;
    private int cardNumber;
    private int age;
    private String loginCredential;
    private String type;

    public User(int cardNumber, String name,int age, String loginCredential, String type) {
        this.age = age;
        this.cardNumber = cardNumber;
        this.name = name;
        this.loginCredential = loginCredential;
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getAge () {
        return  age;
    }

    public String getName() {
        return name;
    }

    public String getLoginCredential() {
        return loginCredential;
    }

    public String toString() {
        return  "["+name + ", " + cardNumber + ", " + age + ", " + loginCredential + ", " + type+"]" ;
    }
}
