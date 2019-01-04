package com.moiz.easyemployee.network;



import com.moiz.easyemployee.model.AttendanceResponse;
import com.moiz.easyemployee.model.RestResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServices {

    public static final String ERROR_MESSAGE = "Server is not reachable at the moment please try again later";

    private ApiServices(){}


    public static void postAttendance(String loginName, String password, String attendanceType, String latitude, String longitude, String inOut, final OnResponseReceivedListener listener)
    {
        RestClient.getAuthAdapter().postAttendance(loginName,password,attendanceType,latitude,longitude,inOut).enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
                if(response.body() != null)
                    listener.onSuccessReceived(response.body());
                else
                    listener.onFailureReceived("No Response From Server");

            }

            @Override
            public void onFailure(Call<RestResponse> call, Throwable t) {
                listener.onFailureReceived(ERROR_MESSAGE);
            }
        });
    }


    public static void postLogin(String username,String password,String mac,final OnResponseReceivedListener listener)
    {
        RestClient.getAuthAdapter().postLogin(username,password,mac).enqueue(new Callback<RestResponse>() {
            @Override
            public void onResponse(Call<RestResponse> call, Response<RestResponse> response) {
                if(response.body() != null)
                    listener.onSuccessReceived(response.body());
                else
                    listener.onFailureReceived("No Response From Server");

            }

            @Override
            public void onFailure(Call<RestResponse> call, Throwable t) {
                listener.onFailureReceived(ERROR_MESSAGE);
            }
        });
    }


    public static void getAttendance(String username, String password, String month, final OnResponseReceivedListener listener)
    {
        RestClient.getAuthAdapter().getAttendance(username,password,month).enqueue(new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                if(response.body() != null)
                    listener.onSuccessReceived(response.body());
                else
                    listener.onFailureReceived("No Response From Server");
            }

            @Override
            public void onFailure(Call<AttendanceResponse> call, Throwable t) {
                listener.onFailureReceived(ERROR_MESSAGE);
            }
        });
    }
}
