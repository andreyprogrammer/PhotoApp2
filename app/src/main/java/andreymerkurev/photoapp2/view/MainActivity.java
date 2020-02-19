package andreymerkurev.photoapp2.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import andreymerkurev.photoapp2.R;
import andreymerkurev.photoapp2.app.App;
import andreymerkurev.photoapp2.model.entity.Hit;
import andreymerkurev.photoapp2.presenter.MainPresenter;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class MainActivity extends MvpAppCompatActivity implements SharedPrefs, MainView {
    private static final String TAG = "app_log - MainActivity";
    private static final String KEY = "KEY";
    private final int SPANCOUNT = 2;
    private RecyclerViewAdapter adapter;
    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getAppComponent().inject(presenter);
        initRecyclerView();
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public boolean isFirstStart() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        return (sharedPreferences.getString(KEY, "isFirstStart").equals("isFirstStart"));
    }

    @Override
    public void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY, "isSecondStart");
        editor.apply();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, SPANCOUNT);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(presenter.getRecyclerMainPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v, int position, List<Hit> hitList) {
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        intent.putExtra("POSITION", hitList.get(position).webformatURL);
        startActivity(intent);
    }

    @Override
    public void progressBarSetVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }

}
