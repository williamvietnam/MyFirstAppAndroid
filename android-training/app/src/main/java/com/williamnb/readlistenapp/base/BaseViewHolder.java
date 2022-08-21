package com.williamnb.readlistenapp.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int currentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        this.currentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }
}
