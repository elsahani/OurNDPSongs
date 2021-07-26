package sg.edu.rp.c346.id20023841.myndpsongslesson10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

        Context parent_context;
        int layout_id;
        ArrayList<Song> versionList;

        public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
            super(context,resource,objects);

            parent_context = context;
            layout_id = resource;
            versionList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Obtain the LayoutInflater object
            LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // "Inflate" the View for each row
            View rowView = inflater.inflate(layout_id, parent, false);

            // Obtain the UI components and do the necessary binding
            TextView tvSongTitle = rowView.findViewById(R.id.tvSongTitle);
            TextView tvSongDate = rowView.findViewById(R.id.tvDate);
            TextView tvRating = rowView.findViewById(R.id.tvRating);
            TextView tvSinger = rowView.findViewById(R.id.tvSingerName);

            // Obtain the Android Version information based on the position
            Song currentVersion = versionList.get(position);

            // Set values to the TextView to display the corresponding information
            tvSongTitle.setText(currentVersion.getTitle());
            tvSongDate.setText(Integer.toString(currentVersion.getYear()));
            tvRating.setText(currentVersion.toString());
            tvSinger.setText(currentVersion.getSingers());

            return rowView;
        }

    }
