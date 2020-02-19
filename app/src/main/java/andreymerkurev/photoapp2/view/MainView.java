package andreymerkurev.photoapp2.view;

import android.view.View;

import java.util.List;

import andreymerkurev.photoapp2.model.entity.Hit;
import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView();

    @StateStrategyType(value = SkipStrategy.class)
    void onClick(View v, int position, List<Hit> hitList);
}
