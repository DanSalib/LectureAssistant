package com.lecture.salib.lectureassistant;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    public static final String EXTRA_DATA = "com.lecture.salib.lectureassistant.EXTRA_DATA";
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"ali", "charbel", "heba", "andrew"};
        ListAdapter lectureAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView lectureListView = (ListView) findViewById(R.id.listView);
        lectureListView.setAdapter(lectureAdapter);

        lectureListView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = String.valueOf(parent.getItemAtPosition(position));
                    openListDataActivity(item);
                    //Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
                }
            }
        );

        Button listenButton = (Button) findViewById(R.id.listenButton);

        listenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpeechActivity();
            }
        });
    }

    public void openListDataActivity(String text) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_DATA, text);

        startActivity(intent);
    }

    public void openSpeechActivity() {
        Intent intent = new Intent(this, speechActivity.class);

        startActivity(intent);
    }


}
