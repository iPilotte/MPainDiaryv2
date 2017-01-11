package kku.mhealth.hackathon.mpaindiaryv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DiaryCreateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_create);

        // Create sample data
        Diarylog diaryToday = new Diarylog();
        //diaryToday.id = 1;
        diaryToday.date = "Steph";
        diaryToday.painLevel = 0;
        diaryToday.painPoint = "xx";
        diaryToday.activity = "0,1,1,1";

        // Get singleton instance of database
        DBConnect databaseHelper = DBConnect.getInstance(this);

        // Add sample post to the database
        databaseHelper.addDiary(diaryToday);

        finish();
        startActivity(new Intent(this,DiaryListActivity.class));

    }
}
