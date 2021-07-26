package sg.edu.rp.c346.id20023841.myndpsongslesson10;
import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int _id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int _id, String title, String singers, int year, int stars) {
        this._id = _id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int get_id() {
        return _id;
    }

    public Song set_id(int id) {
        this._id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSingers() {
        return singers;
    }

    public Song setSingers(String singers) {
        this.singers = singers;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Song setYear(int year) {
        this.year = year;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Song setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += "* ";
        }
        return starsString;

    }

}

