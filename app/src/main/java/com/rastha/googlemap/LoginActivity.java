package com.rastha.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DBHelper.USER_ID,
                                        DBHelper.F_NAME,
                                            DBHelper.USERNAME};
    final int[] to = new int[] { R.id._id, R.id.firstname, R.id.username };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_list);
        dbManager= new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView fnameTextView = (TextView) view.findViewById(R.id.firstname);
                TextView userTextView = (TextView) view.findViewById(R.id.username);
            }
        });

        }
    }

