package com.example.davidnaing.userlist.data.models;

import com.example.davidnaing.userlist.data.vo.UserVO;
import com.example.davidnaing.userlist.events.RestApiEvent;
import com.example.davidnaing.userlist.network.UserDataAgientImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class UserModel {

    private static UserModel sObjInstance;
    private NavigableMap<String, UserVO> mUserMap;

    private UserModel(){
        EventBus.getDefault().register(this);
        mUserMap = new TreeMap<>();
    }

    public void startLoadingUserData(){
        UserDataAgientImpl.getInstance().loadUsers();
    }

    public static UserModel getInstance(){
        if(sObjInstance == null){
            sObjInstance = new UserModel();
        }
        return sObjInstance;
    }

    public UserVO getUserById(String id){
        return mUserMap.get(id);
    }

    public String getNextUser(String id){
        Map.Entry<String,UserVO> next = mUserMap.higherEntry(id);
        if(next == null){
            return null;
        }
        return  next.getKey();
    }

    public String getPreviousUser(String id){
        Map.Entry<String,UserVO> previous = mUserMap.lowerEntry(id);
        if(previous == null){
            return null;
        }
        return previous.getKey();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onUserDataLoaded(RestApiEvent.UserDataLoadedEvent event){
        for(UserVO userVO : event.getUserVOList()){
            mUserMap.put(userVO.getId(),userVO);
        }
    }
}
