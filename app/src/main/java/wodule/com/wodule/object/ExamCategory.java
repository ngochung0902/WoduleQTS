package wodule.com.wodule.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 06/10/2017.
 */
public class ExamCategory {
    @SerializedName("identifier")
    @Expose
    private Integer identifier;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("questioner")
    @Expose
    private Object questioner;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("subject")
    @Expose
    private Integer subject;
    @SerializedName("admin")
    @Expose
    private Integer admin;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("lastChange")
    @Expose
    private String lastChange;
    @SerializedName("deletedDate")
    @Expose
    private Object deletedDate;

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Object getQuestioner() {
        return questioner;
    }

    public void setQuestioner(Object questioner) {
        this.questioner = questioner;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public Object getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Object deletedDate) {
        this.deletedDate = deletedDate;
    }

    public ExamCategory(Integer identifier, Integer number, Object questioner, String photo, String detail, Integer score, Integer subject, Integer admin, String creationDate, String lastChange, Object deletedDate) {
        this.identifier = identifier;
        this.number = number;
        this.questioner = questioner;
        this.photo = photo;
        this.detail = detail;
        this.score = score;
        this.subject = subject;
        this.admin = admin;
        this.creationDate = creationDate;
        this.lastChange = lastChange;
        this.deletedDate = deletedDate;
    }

    public ExamCategory(Integer identifier, Integer number, Object questioner, String detail, Integer score, Integer subject, Integer admin, String creationDate, String lastChange, Object deletedDate) {
        this.identifier = identifier;
        this.number = number;
        this.questioner = questioner;
        this.detail = detail;
        this.score = score;
        this.subject = subject;
        this.admin = admin;
        this.creationDate = creationDate;
        this.lastChange = lastChange;
        this.deletedDate = deletedDate;
    }
}
