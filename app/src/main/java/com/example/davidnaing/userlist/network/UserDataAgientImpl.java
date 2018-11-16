package com.example.davidnaing.userlist.network;

import com.example.davidnaing.userlist.data.vo.UserVO;
import com.example.davidnaing.userlist.events.RestApiEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDataAgientImpl implements UserDataAgient {

    private static UserDataAgientImpl sObjInstance;
    private UserAPI theAPI;

    public UserDataAgientImpl(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();
        theAPI = retrofit.create(UserAPI.class);
    }

    public static UserDataAgientImpl getInstance(){
        if(sObjInstance == null){
            sObjInstance = new UserDataAgientImpl();
        }
        return  sObjInstance;
    }

    @Override
    public void loadUsers() {
        Call<ArrayList<UserVO>> callUserList = theAPI.getAllUsers();
        callUserList.enqueue(new Callback<ArrayList<UserVO>>() {
            @Override
            public void onResponse(Call<ArrayList<UserVO>> call, Response<ArrayList<UserVO>> response) {
                if (!response.body().isEmpty() && response.isSuccessful()
                        && response.body().size() > 0) {
                    // if response success, broadcast received data
                    RestApiEvent.UserDataLoadedEvent userDataLoadedEvent =
                            new RestApiEvent.UserDataLoadedEvent(response.body());
                    EventBus.getDefault().post(userDataLoadedEvent);
                }else {
                    RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                            new RestApiEvent.ErrorInvokingAPIEvent("Response Error.");
                    EventBus.getDefault().post(errorEvent);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserVO>> call, Throwable t) {
                RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                        new RestApiEvent.ErrorInvokingAPIEvent("Response failure.");
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
