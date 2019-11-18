package andreymerkurev.photoapp2.presenter;

import android.app.Activity;

import andreymerkurev.photoapp2.view.DetailActivity;
import andreymerkurev.photoapp2.view.DetailView;
import moxy.MvpPresenter;

public class DetailPresenter extends MvpPresenter<DetailView> {
    public void getImage(Activity activity, String url) {
        ((DetailActivity)activity).setImage(url);
    }
}
