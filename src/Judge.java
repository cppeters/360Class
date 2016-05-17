/**
 * Created by lizmiller on 5/12/16.
 */
public class Judge extends User{

    public Judge(int cardNumber, String name,int age, String loginCredential, boolean isAdmin, boolean isJudge) {
        super(cardNumber,name,age,loginCredential,isAdmin,isJudge);
    }
    /*

    ????
    If we are going to add a judge class what will they be doing. we can access all the contests from the contest class

    POSSIBLY... have this class write to a new db. and this will be the ratings db
    where we can then find all the contests that this person has rated by using their library card? just a thought

     */

}
