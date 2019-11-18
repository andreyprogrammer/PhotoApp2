package andreymerkurev.photoapp2.model.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_pict")
public class Pict {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String webformatURL;

    @Override
    public String toString() {
        return "Pict{" +
                "id=" + id +
                ", webformatURL='" + webformatURL + '\'' +
                '}';
    }
}
