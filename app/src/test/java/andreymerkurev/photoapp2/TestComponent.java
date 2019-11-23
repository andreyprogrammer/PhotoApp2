package andreymerkurev.photoapp2;

import javax.inject.Singleton;

import andreymerkurev.photoapp2.presenter.MainPresenter;
import dagger.Component;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {
    void inject(MainPresenter mainPresenter);
}
