package wodule.com.wodule.utils;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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
}
