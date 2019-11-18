package andreymerkurev.photoapp2.presenter;

import android.view.View;

import andreymerkurev.photoapp2.view.IViewHolder;

public interface IRecyclerMainPresenter {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();
    void onClick(View v, int position);
}
