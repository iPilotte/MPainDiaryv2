package kku.mhealth.hackathon.mpaindiaryv2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DiaryAdapter extends BaseAdapter {
    //Explicit
    private Context context;
    private String[] diaryDate;
    private TextView dDateTextView;

    public DiaryAdapter(Context context, String[] diaryDate) {
        this.context = context;
        this.diaryDate = diaryDate;
    }

    @Override
    public int getCount() {
        return diaryDate.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.diary_card_layout, parent, false);

        //Bind widget
        dDateTextView = (TextView)view.findViewById(R.id.textView);

        dDateTextView.setText(diaryDate[position]);

        return view;
    }
}
