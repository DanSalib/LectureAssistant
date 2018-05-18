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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    public static final String EXTRA_DATA = "com.lecture.salib.lectureassistant.EXTRA_DATA";
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        String[] items = {"ali", "charbel", "heba", "andrew"};
        ListAdapter lectureAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        ListView lectureListView = (ListView) findViewById(R.id.listView);
        lectureListView.setAdapter(lectureAdapter);

        lectureListView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = String.valueOf(parent.getItemAtPosition(position));
                    openActivity2(item);
                    //Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
                }
            }
        );
    }

    public void openActivity2(String text) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_DATA, text);

        startActivity(intent);
    }

}
