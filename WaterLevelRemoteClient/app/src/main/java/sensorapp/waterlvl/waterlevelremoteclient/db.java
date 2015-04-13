package sensorapp.waterlvl.waterlevelremoteclient;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class db extends ListActivity {

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ListView tankList;
    protected Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        db = (new DatabaseHelper(this)).getWritableDatabase();
        searchText = (EditText) findViewById(R.id.searchText);
    }
    public void search(View view) {
        // || is the concatenation operation in SQLite
        cursor = db.rawQuery("SELECT * FROM wtrlvl WHERE TANK_NAME || ' ' || TANK_NAME LIKE ?",
                new String[]{"%" + searchText.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.tank_list_item,
                cursor,
                new String[]{"TANK_NAME", "UID"},
                new int[]{R.id.Tankname, R.id.UID});
        setListAdapter(adapter);
    }
    public void onListItemClick(ListView parent, View view, int position, long id) {
        Intent intent = new Intent(this, Sensor_out.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("sUID", cursor.getString(cursor.getColumnIndex("UID")));
        intent.putExtra("tNAME", cursor.getString(cursor.getColumnIndex("TANK_NAME")));
        startActivity(intent);
    }




}