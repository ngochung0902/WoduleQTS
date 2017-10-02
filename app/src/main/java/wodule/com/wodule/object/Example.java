package wodule.com.wodule.object;

/**
 * Created by MyPC on 18/09/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("user")
    @Expose
    private UserObject user;


    public UserObject getUser() {
        return user;
    }

    public void setUser(UserObject user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Example{" +
                "user=" + user +
                '}';
    }
}