package pl.pzjapp.project.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "city_list", indices = {@Index("name"), @Index(value = {"city_id", "preset_id"})},
        foreignKeys = {
                @ForeignKey(entity = City.class, parentColumns = "id", childColumns = "city_id"),
                @ForeignKey(entity = Preset.class, parentColumns = "id", childColumns = "preset_id")})
public class CityList {
    @ColumnInfo(name = "city_id")
    public int cityId;
    @ColumnInfo(name = "preset_id")
    public int presetId;
}
