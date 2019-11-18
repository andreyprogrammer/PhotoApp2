package andreymerkurev.photoapp2.model.retrofit;

import andreymerkurev.photoapp2.model.entity.Photo;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiService {
    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
