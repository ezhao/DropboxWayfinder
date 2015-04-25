package com.herokuapp.ezhao.dropboxwayfinder;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTextChanged;

public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.lvSearchResults) ListView lvSearchResults;
    @InjectView(R.id.etRoomSearch) EditText etRoomSearch;
    ArrayList<Room> rooms;
    ArrayList<Room> currentRooms;
    RoomAdapter roomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        rooms = Room.fromJsonFile(this);
        currentRooms = new ArrayList<>();
        roomAdapter = new RoomAdapter(this, currentRooms);
        lvSearchResults.setAdapter(roomAdapter);

        getSupportActionBar().hide();
    }

    @OnTextChanged(R.id.etRoomSearch)
    public void onSearch() {
        String prefix = etRoomSearch.getText().toString().toLowerCase();
        if (prefix.length() == 0) {
            currentRooms.clear();
            Log.i("EMILY", "calling clear");
            return;
        }

        // prioritize rooms that have this prefix
        for (Room room : rooms) {
            String roomName = room.getName().toLowerCase();
            Boolean alreadyIn = currentRooms.contains(room);
            if (roomName.startsWith(prefix) || roomName.equals(prefix)) {
                if (currentRooms.size() < 3 && !alreadyIn) {
                    currentRooms.add(room);
                }
            } else if (alreadyIn) {
                currentRooms.remove(room);
            }
        }

        // search whole room name if we don't have enough matches
        for (Room room : rooms) {
            if (currentRooms.size() < 3) {
                String roomName = room.getName().toLowerCase();
                if (roomName.contains(prefix) && !currentRooms.contains(room)) {
                    currentRooms.add(room);
                }
            } else {
                break;
            }
        }

        roomAdapter.notifyDataSetChanged();
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
