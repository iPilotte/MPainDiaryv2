package kku.mhealth.hackathon.mpaindiaryv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class DiaryListActivity extends AppCompatActivity {

    DBConnect dbConnect;
    List<String> dList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
    }
}
