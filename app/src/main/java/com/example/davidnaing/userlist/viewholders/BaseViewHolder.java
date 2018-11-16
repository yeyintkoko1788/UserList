package com.example.davidnaing.userlist.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected W mData;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);
}
