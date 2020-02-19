package andreymerkurev.photoapp2.presenter;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import andreymerkurev.photoapp2.app.App;
import andreymerkurev.photoapp2.model.entity.Hit;
import andreymerkurev.photoapp2.model.entity.Photo;
import andreymerkurev.photoapp2.model.retrofit.ApiHelper;
import andreymerkurev.photoapp2.model.room.AppDatabase;
import andreymerkurev.photoapp2.model.room.Pict;
import andreymerkurev.photoapp2.view.IViewHolder;
import andreymerkurev.photoapp2.view.MainView;
import andreymerkurev.photoapp2.view.SharedPrefs;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private static final String TAG = "app_log - MainPresenter";
    private RecyclerMainPresenter recyclerMainPresenter;
    private List<Hit> hitList;
    private SharedPrefs sharedPrefs;

    @Inject
    ApiHelper apiHelper;

    @Inject
    AppDatabase appDatabase;

    public MainPresenter(SharedPrefs sharedPrefs) {
        recyclerMainPresenter = new RecyclerMainPresenter();
        this.sharedPrefs = sharedPrefs;
    }

    @Override
    protected void onFirstViewAttach() {
        if (sharedPrefs.isFirstStart()) {
            getAllPhotoFromInternet();
            sharedPrefs.savePreferences();
        } else {
            getAllPhotoFromDB();
        }
    }

    public void getAllPhotoFromInternet() {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        Observable<Photo> single = apiHelper.requestServer();
        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            Log.d(TAG, "onNext: " + photos.totalHits);
            for (Hit hit : photos.hits) {
                putData(hit.webformatURL);
            }
            hitList = photos.hits;
            getViewState().progressBarSetVisibility(View.INVISIBLE);
            getViewState().updateRecyclerView();
        }, throwable -> {
            Log.e(TAG, "onError " + throwable);
        });
    }

    public void getAllPhotoFromDB() {
        getViewState().progressBarSetVisibility(View.VISIBLE);
        hitList  = new ArrayList<>();
        Disposable disposable = appDatabase.pictDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(picts -> {
                    for (int i = 0; i < picts.size(); i++) {
                        hitList.add(new Hit(picts.get(i).webformatURL));
                    }
                    getViewState().progressBarSetVisibility(View.INVISIBLE);
                    getViewState().updateRecyclerView();
                }, throwable -> {
                });
    }

    public void putData(String webformatURL) {
        Pict pict = new Pict();
        pict.webformatURL = webformatURL;

        Disposable disposable = appDatabase.pictDao().insert(pict).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    Log.d(TAG, "putData: " + id);
                }, throwable -> {
                    Log.d(TAG, "putData: " + throwable);
                });
    }

    private class RecyclerMainPresenter implements IRecyclerMainPresenter {

        @Override
        public void bindView(IViewHolder holder) {
            holder.setImage(hitList.get(holder.getPos()).webformatURL);
        }

        @Override
        public int getItemCount() {
            if (hitList != null) {
                return hitList.size();
            }
            return 0;
        }

        @Override
        public void onClick(View v, int position) {
            getViewState().onClick(v, position, hitList);
        }
    }

    public RecyclerMainPresenter getRecyclerMainPresenter() {
        return recyclerMainPresenter;
    }

}
