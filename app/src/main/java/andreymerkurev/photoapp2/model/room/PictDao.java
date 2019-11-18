package andreymerkurev.photoapp2.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface PictDao {
    @Query("SELECT * FROM table_pict")
    Single<List<Pict>> getAll();

    @Insert
    Single<Long> insert(Pict pict);
}
