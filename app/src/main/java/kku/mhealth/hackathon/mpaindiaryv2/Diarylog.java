package kku.mhealth.hackathon.mpaindiaryv2;

/**
 * Created by TheButterfly on 11-Jan-17.
 */

public class Diarylog {

    public String date;
    public int painLevel;
    public String painPoint;

    //Default Constructor
    public Diarylog(){}

    //Constructor
    public Diarylog(String date,int painLevel,String painPoint){
        this.date = date;
        this.painLevel = painLevel;
        this.painPoint = painPoint;
    }

}
