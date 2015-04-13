package sensorapp.waterlvl.waterlevelremoteclient;


import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class add_sensor extends ActionBarActivity {

    protected SQLiteDatabase db;
    protected EditText Tanksensor;
    protected EditText SensorCode;
    protected Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);
        db = (new DatabaseHelper(this)).getWritableDatabase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_sensor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void addSnsr(View view)
    {
        Tanksensor = (EditText) findViewById(R.id.Tanksensor);
        SensorCode = (EditText) findViewById(R.id.SensorCode);
        String s1=Tanksensor.getText().toString();
        String s2=SensorCode.getText().toString();
        String s="INSERT INTO wtrlvl (UID, TANK_NAME)VALUES('"+s2+"','"+s1+"')";
        db.execSQL(s);
    }

    public void remSnsr(View view)
    {
        Tanksensor = (EditText) findViewById(R.id.Tanksensor);
        SensorCode = (EditText) findViewById(R.id.SensorCode);
        String s1=Tanksensor.getText().toString();
        String s2=SensorCode.getText().toString();
        String s="DELETE FROM wtrlvl WHERE UID ='"+s2+"' OR TANK_NAME = '"+s1+"'";
        db.execSQL(s);
    }
}
