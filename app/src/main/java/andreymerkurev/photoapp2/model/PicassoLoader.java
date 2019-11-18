package andreymerkurev.photoapp2.model;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import andreymerkurev.photoapp2.R;

public class PicassoLoader {
    public void loadImage(String url, ImageView imageView) {
        Picasso
                .get()
                .load(url)
                .error(R.drawable.smile)
                .into(imageView);

    }
}
