package com.example.davidnaing.userlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidnaing.userlist.R;
import com.example.davidnaing.userlist.data.vo.UserVO;
import com.example.davidnaing.userlist.delegetes.UserItemDelegate;
import com.example.davidnaing.userlist.viewholders.UserViewHolder;

public class UserRVAdapter extends BaseRecyclerAdapter<UserViewHolder,UserVO> {

    private UserItemDelegate mDelegate;

    public UserRVAdapter(Context context, UserItemDelegate delegate) {
        super(context);
        mDelegate = delegate;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new UserViewHolder(view, mDelegate);
    }
}
