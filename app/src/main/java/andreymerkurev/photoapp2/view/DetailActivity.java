package andreymerkurev.photoapp2.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import andreymerkurev.photoapp2.R;
import andreymerkurev.photoapp2.presenter.DetailPresenter;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {
    ImageView imageView;

    @InjectPresenter
    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.img);

        String url = getIntent().getStringExtra("POSITION");
        detailPresenter.getImage(this, url);
    }

    public void setImage(String url) {
        Picasso
                .get()
                .load(url)
                .into(imageView);
    }
}
