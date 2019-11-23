package andreymerkurev.photoapp2;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import andreymerkurev.photoapp2.model.entity.Hit;
import andreymerkurev.photoapp2.model.entity.Photo;
import andreymerkurev.photoapp2.model.retrofit.ApiHelper;
import andreymerkurev.photoapp2.presenter.MainPresenter;
import andreymerkurev.photoapp2.view.MainView;
import andreymerkurev.photoapp2.view.SharedPrefs;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    private MainPresenter mainPresenter;

    @Mock
    MainView mainView;

    @Mock
    SharedPrefs sharedPrefs;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline()
        );
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = Mockito.spy(new MainPresenter(sharedPrefs));
    }

    @Test
    public void getAllPhotoFromInternet_isCorrect() {
        List<Hit> hList;
        hList = hitListCreator("https://pixabay.com/get/52e5dc424b50ad14f6da8c7dda79367a153fd8ed51516c48702879d39048c351bd_640.jpg");
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule() {
                    @Override
                    public ApiHelper provideApiHelper() {
                        ApiHelper apiHelper = super.provideApiHelper();
                        Photo photos = new Photo();
                        photos.hits = hList;
                        Mockito.when(apiHelper.requestServer()).thenReturn(Observable.just(photos));
                        return apiHelper;
                    }
                }).build();
        component.inject(mainPresenter);
        mainPresenter.attachView(mainView);
        mainPresenter.getAllPhotoFromInternet();

        Photo photos = new Photo();
        photos.hits = hList;

        Mockito.verify(mainView).setImage(photos.hits);

    }

    public List<Hit> hitListCreator(String str) {
        List<Hit> hitList = new ArrayList<>();
        Hit hit = new Hit(str);
        hitList.add(hit);
        return hitList;
    }

}
