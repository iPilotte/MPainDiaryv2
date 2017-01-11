package kku.mhealth.hackathon.mpaindiaryv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button diaryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaryButton = (Button)findViewById(R.id.button);

        diaryButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == diaryButton){
            finish();
            startActivity(new Intent(this,DiaryListActivity.class));
        }
    }
}
