package andreymerkurev.photoapp2;

import org.mockito.Mockito;

import andreymerkurev.photoapp2.model.retrofit.ApiHelper;
import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    @Provides
    public ApiHelper provideApiHelper() {
        return Mockito.mock(ApiHelper.class);
    }
}
