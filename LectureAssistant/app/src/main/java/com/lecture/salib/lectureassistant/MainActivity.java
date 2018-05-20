package com.lecture.salib.lectureassistant;

import android.app.DownloadManager;
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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {

    public static final String EXTRA_DATA = "com.lecture.salib.lectureassistant.EXTRA_DATA";
    private JSONArray lectureData = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GetLectures();

        setContentView(R.layout.activity_main);

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

    public void GetLectures() {
        String urlString = "http://192.168.2.16:5000/";
        this.lectureData = new JSONArray();
        final MainActivity mainActivity = this;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(urlString, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(responseBody != null) {
                    try {
                        lectureData = new JSONArray(new String(responseBody));

                        String[] items = new String[lectureData.length()];

                        for(int i = 0; i < lectureData.length(); i++) {
                            items[i] = lectureData.getJSONObject(i).get("title").toString();
                        }

                        ListAdapter lectureAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_list_item_1, items);
                        ListView lectureListView = (ListView) findViewById(R.id.listView);
                        lectureListView.setAdapter(lectureAdapter);

                        lectureListView.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String item = String.valueOf(parent.getItemAtPosition(position));
                                        try {
                                            openListDataActivity(lectureData.getJSONObject(position).getString("description"));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


}
