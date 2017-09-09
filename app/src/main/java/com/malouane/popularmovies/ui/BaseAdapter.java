package com.malouane.popularmovies.ui;

import android.support.v7.widget.RecyclerView;
import java.util.List;

public abstract class BaseAdapter<Type extends RecyclerView.ViewHolder, Data>
    extends RecyclerView.Adapter<Type> {
  public abstract void setData(List<Data> data);
}
