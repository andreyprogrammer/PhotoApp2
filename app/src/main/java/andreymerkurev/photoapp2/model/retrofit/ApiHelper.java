package andreymerkurev.photoapp2.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import andreymerkurev.photoapp2.model.entity.Photo;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    public Observable<Photo> requestServer() {
        String KEY = "14006820-e3048f017fdf21bd6486e618c";
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        IApiService api = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);
        return api.getPhoto(KEY).subscribeOn(Schedulers.io());
    }
}