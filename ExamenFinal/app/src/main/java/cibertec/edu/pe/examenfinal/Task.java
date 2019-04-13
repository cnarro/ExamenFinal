package cibertec.edu.pe.examenfinal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Task {

    @PrimaryKey (autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
