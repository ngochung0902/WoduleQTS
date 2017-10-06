package wodule.com.wodule.object;

/**
 * Created by MyPC on 06/10/2017.
 */
public class LineCategory {
    int id ;
    String tv_to,tv_nho;

    public LineCategory(int id, String tv_to, String tv_nho) {
        this.id = id;
        this.tv_to = tv_to;
        this.tv_nho = tv_nho;
    }

    public LineCategory() {
    }

    public String getTv_to() {
        return tv_to;
    }

    public void setTv_to(String tv_to) {
        this.tv_to = tv_to;
    }

    public String getTv_nho() {
        return tv_nho;
    }

    public void setTv_nho(String tv_nho) {
        this.tv_nho = tv_nho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
