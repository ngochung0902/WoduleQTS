package wodule.com.wodule.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MyPC on 06/10/2017.
 */
public class ListDataExam {
    @SerializedName("data")
    @Expose
    private List<ExamCategory> data = null;

    public List<ExamCategory> getData() {
        return data;
    }

    public void setData(List<ExamCategory> data) {
        this.data = data;
    }

}
