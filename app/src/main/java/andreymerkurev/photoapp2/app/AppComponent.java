package andreymerkurev.photoapp2.app;

import javax.inject.Singleton;

import andreymerkurev.photoapp2.presenter.MainPresenter;
import andreymerkurev.photoapp2.view.DetailActivity;
import andreymerkurev.photoapp2.view.RecyclerViewAdapter;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainPresenter mainPresenter);
    void inject(RecyclerViewAdapter recyclerViewAdapter);
    void inject(DetailActivity detailActivity);
}