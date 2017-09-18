package wodule.com.wodule.utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.object.UserExaminer;

/**
 * Created by MyPC on 14/09/2017.
 */
public interface APIService {
    @POST("api/user_login")
    @FormUrlEncoded
    Call<UserExaminer> savePost(@Field("user_name") String user_name,
                                @Field("password") String password,
                                @Field("social") boolean social);

    @GET("api/profile/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI4LCJpc3MiOiJodHRwOi8vd29kdWxlLmlvL2FwaS91c2VyX2xvZ2luIiwiaWF0IjoxNTA1NzMwOTU4LCJleHAiOjE1MDU3MzQ1NTgsIm5iZiI6MTUwNTczMDk1OCwianRpIjoibmJqVHJVc1FXTEdIOGtSUCJ9.2HvlZXasZNcGiCWX5bIoPNaweIS7dtHQM-ZGKYTe07w")
    //@Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI4LCJpc3MiOiJodHRwOi8vd29kdWxlLmlvL2FwaS91c2VyX2xvZ2luIiwiaWF0IjoxNTA1NzMwOTU4LCJleHAiOjE1MDU3MzQ1NTgsIm5iZiI6MTUwNTczMDk1OCwianRpIjoibmJqVHJVc1FXTEdIOGtSUCJ9.2HvlZXasZNcGiCWX5bIoPNaweIS7dtHQM-ZGKYTe07w")
    Call <Example> getAnswers();
}
