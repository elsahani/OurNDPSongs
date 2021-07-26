package sg.edu.rp.c346.id20023841.myndpsongslesson10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etSongTitle, etSongSinger, etSongYear, etSongID;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStars;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    Song song;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etSongID = findViewById(R.id.etSongID);
        etSongSinger = findViewById(R.id.etSingersName);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSongYear = findViewById(R.id.etModifiedYear);
        btnCancel = findViewById(R.id.buttonCancel);
        btnDelete = findViewById(R.id.buttonDelete);
        btnUpdate = findViewById(R.id.buttonUpdate);
        rgStars = findViewById(R.id.rgStars);
        rb1 = findViewById(R.id.one);
        rb2 = findViewById(R.id.two);
        rb3 = findViewById(R.id.three);
        rb4 = findViewById(R.id.four);
        rb5 = findViewById(R.id.five);


        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        etSongID.setText(song.get_id() + "");
        etSongTitle.setText(song.getTitle());
        etSongSinger.setText(song.getSingers());
        etSongYear.setText(song.getYear() + "");

        if(song.getStars()==1){
            rb1.setChecked(true);
        } else if (song.getStars()==2){
            rb2.setChecked(true);
        } else if (song.getStars()==3){
            rb3.setChecked(true);
        } else if (song.getStars()==4) {
            rb4.setChecked(true);
        } else if (song.getStars()==5) {
            rb5.setChecked(true);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                int result = dbh.deleteSong(song.get_id());
                if (result>0){
                    Toast.makeText(EditActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
                finish();

            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                song.setTitle(etSongTitle.getText().toString().trim());
                song.setSingers(etSongSinger.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etSongYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                song.setYear(year);

                int selectedRB = rgStars.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);
                song.setStars(Integer.parseInt(rb.getText().toString()));
                int result = dbh.updateSong(song);
                if (result>0){
                    Toast.makeText(EditActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}