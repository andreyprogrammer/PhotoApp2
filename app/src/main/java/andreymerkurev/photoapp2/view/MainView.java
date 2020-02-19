package andreymerkurev.photoapp2.view;

import android.view.View;

import androidx.annotation.IntDef;

import java.util.List;

import andreymerkurev.photoapp2.model.entity.Hit;
import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = SkipStrategy.class)
public interface MainView extends MvpView {
    void updateRecyclerView();
    void onClick(View v, int position, List<Hit> hitList);
    void progressBarSetVisibility(int visibility);
}