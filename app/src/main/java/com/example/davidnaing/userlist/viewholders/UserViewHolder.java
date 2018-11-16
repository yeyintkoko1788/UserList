package com.example.davidnaing.userlist.viewholders;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidnaing.userlist.R;
import com.example.davidnaing.userlist.data.vo.UserVO;
import com.example.davidnaing.userlist.delegetes.UserItemDelegate;

import java.lang.reflect.Field;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends BaseViewHolder<UserVO> {
    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.tv_email)
    TextView tvEmail;

    @BindView(R.id.imageView)
    ImageView profileImage;

    private UserItemDelegate mDelegate;

    private Context context;

    public UserViewHolder(View  itemView, UserItemDelegate delegate){
        super(itemView);

        mDelegate = delegate;
        ButterKnife.bind(this,itemView);
    }
    @Override
    public void setData(UserVO data) {
        final TypedArray imgs = itemView.getContext().getResources().obtainTypedArray(R.array.profile_image);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        final int resID = imgs.getResourceId(rndInt, 0);
        profileImage.setImageResource(resID);
        mData = data;
        tvName.setText(mData.getName());
        tvPhone.setText(mData.getPhone());
        tvEmail.setText(mData.getEmail());
        tvEmail.setPaintFlags(tvEmail.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
        mDelegate.OnTapUser(mData);
    }
}
