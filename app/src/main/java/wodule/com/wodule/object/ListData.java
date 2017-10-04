package wodule.com.wodule.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MyPC on 04/10/2017.
 */
public class ListData {
    @SerializedName("data")
    @Expose
    private List<Ahistory> data = null;

    public List<Ahistory> getData() {
        return data;
    }

    public void setData(List<Ahistory> data) {
        this.data = data;
    }
}
