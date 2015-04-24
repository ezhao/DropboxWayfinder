package com.herokuapp.ezhao.dropboxwayfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class RoomAdapter extends ArrayAdapter<Room> {
    public RoomAdapter(Context context, List<Room> rooms) {
        super(context, 0, rooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Room room = getItem(position);

        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_room, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.tvRoomName.setText(room.getName());
        holder.tvSize.setText(room.getSize());
        holder.tvFloor.setText(room.getFloor());

        List<String> landmarksRaw = room.getLandmarks();
        if (landmarksRaw.size() > 0) {
            String landmarks = landmarksRaw.get(0);
            for (int i = 1; i < landmarksRaw.size(); i++) {
                String landmark = landmarksRaw.get(i);
                landmarks = landmarks + ", " + landmark;
            }
            holder.tvLandmarks.setText(landmarks);
        }

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tvRoomName) TextView tvRoomName;
        @InjectView(R.id.tvLandmarks) TextView tvLandmarks;
        @InjectView(R.id.tvSize) TextView tvSize;
        @InjectView(R.id.tvFloor) TextView tvFloor;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
