package com.example.davidnaing.userlist.network;

import com.example.davidnaing.userlist.data.vo.UserVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPI {
    @GET("/users")
    Call<ArrayList<UserVO>> getAllUsers();
}
