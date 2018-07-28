package com.codencodesoftware.easyemployeeapp.network;


import com.codencodesoftware.easyemployeeapp.model.AttendanceResponse;
import com.codencodesoftware.easyemployeeapp.model.RestResponse;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * Created by Moiz Ahmed
 */
public interface ApiInterface {


    @POST(EndPoints.postAttendance)
    Call<RestResponse> postAttendance(@Query("loginName") String user, @Query("password") String password, @Query("attendanceType") String attendanceType, @Query("lat") String lat, @Query("lng") String lng, @Query("in_out") String in_out);

    @POST(EndPoints.login)
    Call<RestResponse> postLogin(@Query("USER_NAME") String user, @Query("PASSWORD") String password);

    @GET(EndPoints.getAttendance)
    Call<AttendanceResponse> getAttendance(@Query("USER_NAME") String user, @Query("PASSWORD") String password, @Query("MONTH") String month);

}
