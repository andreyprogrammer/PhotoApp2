package andreymerkurev.photoapp2.presenter;

import andreymerkurev.photoapp2.view.DetailView;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {
    public void getImage(String url) {
        getViewState().setImage(url);
    }
}
