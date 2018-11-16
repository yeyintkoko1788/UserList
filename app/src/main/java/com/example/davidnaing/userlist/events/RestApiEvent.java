package com.example.davidnaing.userlist.events;

import com.example.davidnaing.userlist.data.vo.UserVO;

import java.util.List;

public class RestApiEvent {

    public static class EmptyResponseEvent{
        private String message;

        public EmptyResponseEvent(String message) {
            this.message = message;
        }

        public String getEmptyResponseMsg(){
            return message;
        }
    }

    public static class ErrorInvokingAPIEvent {
        private String errorMessage;

        public ErrorInvokingAPIEvent(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage(){
            return errorMessage;
        }
    }

    public static class UserDataLoadedEvent{
        private List<UserVO> userVOList;

        public UserDataLoadedEvent(List<UserVO> countryVOList) {
            this.userVOList = countryVOList;
        }

        public List<UserVO> getUserVOList(){
            return userVOList;
        }
    }
}
