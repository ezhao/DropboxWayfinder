package com.herokuapp.ezhao.dropboxwayfinder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    ArrayList<Room> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rooms = Room.fromJsonFile(this);
        Log.i("EMILY", rooms.toString());
        Room room = rooms.get(0);
        Log.i("EMILY", room.getName());
        Log.i("EMILY", room.getFloor());
        Log.i("EMILY", room.getKey());
        Log.i("EMILY", room.getSize());
        Log.i("EMILY", room.getLandmarks().toString());
        Log.i("EMILY", room.getLandmarks().get(0));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
