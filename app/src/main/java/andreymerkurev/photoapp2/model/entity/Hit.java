package andreymerkurev.photoapp2.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {
    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

    public Hit(String webformatURL) {
        this.webformatURL = webformatURL;
    }
}
