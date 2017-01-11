package kku.mhealth.hackathon.mpaindiaryv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class DiaryListActivity extends AppCompatActivity {

    DBConnect dbConnect;
    List<String> dList;

    private ListView listView;
    private String[] diaryDate;
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        testButton = (Button) findViewById(R.id.button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(DiaryListActivity.this, DiaryCreateActivity.class));
            }
        });

        //Bind widget
        listView = (ListView) findViewById(R.id.diaryList);

        dbConnect = new DBConnect(this);
        dList = dbConnect.getDiary();

        diaryDate = new String[dList.size()];
        diaryDate = dList.toArray(diaryDate);

        DiaryAdapter adapter = new DiaryAdapter(DiaryListActivity.this, diaryDate);
        listView.setAdapter(adapter);


    }


}
