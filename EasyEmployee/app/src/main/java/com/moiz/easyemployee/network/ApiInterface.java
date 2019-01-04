package com.moiz.easyemployee.network;




import java.io.File;
import java.util.List;

import com.moiz.easyemployee.model.AttendanceResponse;
import com.moiz.easyemployee.model.RestResponse;
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
    Call<RestResponse> postLogin(@Query("USER_NAME") String user, @Query("PASSWORD") String password, @Query("macAddress") String mac);

    @GET(EndPoints.getAttendance)
    Call<AttendanceResponse> getAttendance(@Query("USER_NAME") String user, @Query("PASSWORD") String password, @Query("MONTH") String month);

}
