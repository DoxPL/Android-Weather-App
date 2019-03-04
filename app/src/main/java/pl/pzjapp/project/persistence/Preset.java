package pl.pzjapp.project.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "preset")
public class Preset {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    public Preset() {
    }//TODO: Validate if default constructor is enough for the Android Persistence module.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
