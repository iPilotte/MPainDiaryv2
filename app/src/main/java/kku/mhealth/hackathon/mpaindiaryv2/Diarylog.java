package kku.mhealth.hackathon.mpaindiaryv2;

/**
 * Created by TheButterfly on 11-Jan-17.
 */

public class Diarylog {

    public int id;
    public String date;
    public int painLevel;
    public String painPoint;
    public String activity;

    //Default Constructor
    public Diarylog(){}

    //Constructor
    public Diarylog(int id,String date,int painLevel,String painPoint,String activity){
        this.id = id;
        this.date = date;
        this.painLevel = painLevel;
        this.painPoint = painPoint;
        this.activity = activity;
    }

}
