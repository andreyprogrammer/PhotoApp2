package andreymerkurev.photoapp2.app;

import android.app.Application;

import javax.inject.Singleton;

import andreymerkurev.photoapp2.model.PicassoLoader;
import andreymerkurev.photoapp2.model.retrofit.ApiHelper;
import andreymerkurev.photoapp2.model.room.AppDatabase;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    ApiHelper provideApiHelper() {
        return new ApiHelper();
    }

    @Singleton
    @Provides
    PicassoLoader providePicassoLoader() {
        return new PicassoLoader();
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase() {
        return App.getAppDatabase();
    }
}
