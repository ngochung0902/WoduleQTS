package wodule.com.wodule.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 14/09/2017.
 */
public class UserExaminer {
    @SerializedName("user_name")
    @Expose
    String user_name;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("social")
    @Expose
    boolean social;
    @SerializedName("token")
    @Expose
    String token;
    @SerializedName("first")
    @Expose
    String first;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public boolean isSocial() {
        return social;
    }

    public void setSocial(boolean social) {
        this.social = social;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserExaminer{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", social=" + social +
                ", token='" + token + '\'' +
                ", first='" + first + '\'' +
                '}';
    }
}
