package wodule.com.wodule.utils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import wodule.com.wodule.object.Example;
import wodule.com.wodule.object.ListData;
import wodule.com.wodule.object.UserExaminer;
import wodule.com.wodule.object.UserObject;

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


    @Multipart
    @POST("api/user_register")
    Call<UserObject> postAPI(
            @Part("city") RequestBody city,
            @Part("country") RequestBody  country,
            @Part("telephone") RequestBody  telephone,
            @Part("nationality") RequestBody  nationality,
            @Part("status") RequestBody  status,
            @Part("gender") RequestBody  gender,
            @Part("user_name") RequestBody  user_name,
            @Part("email") RequestBody  email,
            @Part("password") RequestBody  password,
            @Part("code") RequestBody  code,
            @Part("first_name") RequestBody  first_name,
            @Part("middle_name") RequestBody  middle_name,
            @Part("last_name") RequestBody  last_name,
            @Part("date_of_birth") RequestBody  date_of_birth,
            @Part("country_of_birth") RequestBody  country_of_birth,
            @Part("native_name") RequestBody  native_name,
            @Part("Suffx") RequestBody  Suffx,
            @Part("Ln_first") RequestBody  ln_first,
            @Part("address") RequestBody  address,
            @Part("ethnicity") RequestBody  ethnicity,
            @Part("religion") RequestBody  religion,
            @Part("organization") RequestBody  organization,
            @Part("student_class") RequestBody  student_class,
            @Part("adviser") RequestBody  adviser,
            @Part MultipartBody.Part picture
    );

    @GET("api/users/28/records")
    Call <ListData> getAhistory(@Header("Authorization")String auth);
}
