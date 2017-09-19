package wodule.com.wodule.utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.object.User;
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

    @GET("api/profile")
    Call <Example> getAnswers(@Header("Authorization")String auth);

    @POST("api/user_register")
    @FormUrlEncoded
    Call<User> postRegister(
            @Field("In_first") String ln_first,
            @Field("address") String address,
            @Field("ethnicity") String ethnicity,
            @Field("religion") String religion,
            @Field("picture") String picture,
            @Field("email") String email,
            @Field("last_name") String last_name,
            @Field("city") String city,
            @Field("nationality") String nationality,
            @Field("code") String code,
            @Field("password") String password,
            @Field("native_name") String native_name,
            @Field("Suffx") String Suffx,
            @Field("first_name") String first_name,
            @Field("date_of_birth") String date_of_birth,
            @Field("country") String country,
            @Field("status") String status,
            @Field("user_name") String user_name,
            @Field("middle_name") String middle_name,
            @Field("country_of_birth") String country_of_birth,
            @Field("telephone") String telephone,
            @Field("gender") String gender);
}
