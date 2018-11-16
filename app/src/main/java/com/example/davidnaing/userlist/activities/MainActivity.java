package com.example.davidnaing.userlist.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.davidnaing.userlist.R;
import com.example.davidnaing.userlist.adapters.UserRVAdapter;
import com.example.davidnaing.userlist.data.models.UserModel;
import com.example.davidnaing.userlist.data.vo.UserVO;
import com.example.davidnaing.userlist.delegetes.UserItemDelegate;
import com.example.davidnaing.userlist.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements UserItemDelegate {
    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private UserRVAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new UserRVAdapter(this, this);
        mRecyclerView.setAdapter(adapter);

        UserModel.getInstance().startLoadingUserData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvent.UserDataLoadedEvent event) {
        adapter.appendNewData(event.getUserVOList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Toast.makeText(this, event.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnTapUser(UserVO userVO) {
        Intent intent = UserDetailActivity.getNewIntent(this, userVO.getId());
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in,0);
    }

}
