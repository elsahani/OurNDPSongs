package sg.edu.rp.c346.id20023841.ourndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnFilter;
    ArrayList<Song> al;
    ArrayList<String> years;
    ListView lv;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    Song song;

    CustomAdapter caToDo;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        caToDo.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getYears());
        spinnerAdapter.notifyDataSetChanged();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        DBHelper dbh = new DBHelper(ShowActivity.this);

        btnFilter = findViewById(R.id.buttonFilter);
        lv = findViewById(R.id.lvSongs);
        spinner = findViewById(R.id.spinnerYear);

        al = dbh.getAllSongs();
        years = dbh.getYears();
        dbh.close();

        caToDo = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(caToDo);

        caToDo.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowActivity.this, EditActivity.class);
                i.putExtra("song", al.get(position));
                startActivity(i);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsByStars(5));
                caToDo.notifyDataSetChanged();

            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsByYear(Integer.valueOf(years.get(position))));
                caToDo.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}