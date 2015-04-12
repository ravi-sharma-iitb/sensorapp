package sensorapp.waterlvl.waterlevelremoteclient;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class db extends ActionBarActivity {

    private Toolbar toolbar;

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ListView studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Database");
        toolbar.setLogo(R.mipmap.ic_launcher);
        db = (new DatabaseHelper(this)).getWritableDatabase();
        searchText = (EditText) findViewById(R.id.searchText);
        studentList = (ListView) findViewById(R.id.list);
    }
    public void search(View view) {
        // || is the concatenation operation in SQLite
        cursor = db.rawQuery("SELECT * FROM wtrlvl WHERE TANK_NAME = ?", new String[]{""});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.student_list_item,
                cursor,
                new String[]{"TANK_NAME", "UID"},
                new int[]{R.id.Tankname, R.id.UID});
        studentList.setAdapter(adapter);
    }

}
