package wodule.com.wodule.utils;

/**
 * Created by MyPC on 08/08/2017.
 */
public class APIUtils {
    private APIUtils() {}

    public static final String BASE_URL = "http://wodule.io/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
