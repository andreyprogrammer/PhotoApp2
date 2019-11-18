package andreymerkurev.photoapp2.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pict.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PictDao pictDao();
}
