package wodule.com.wodule.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MyPC on 04/10/2017.
 */
public class Ahistory {
    @SerializedName("identifier")
    @Expose
    private Integer identifier;
    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("exam")
    @Expose
    private String exam;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("examCategory")
    @Expose
    private String examCategory;
    @SerializedName("examDetails")
    @Expose
    private String examDetails;
    @SerializedName("examQuestionaire")
    @Expose
    private String examQuestionaire;
    @SerializedName("examinee")
    @Expose
    private Integer examinee;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("lastChange")
    @Expose
    private String lastChange;
    @SerializedName("deletedDate")
    @Expose
    private Object deletedDate;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getExamCategory() {
        return examCategory;
    }

    public void setExamCategory(String examCategory) {
        this.examCategory = examCategory;
    }

    public String getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(String examDetails) {
        this.examDetails = examDetails;
    }

    public String getExamQuestionaire() {
        return examQuestionaire;
    }

    public void setExamQuestionaire(String examQuestionaire) {
        this.examQuestionaire = examQuestionaire;
    }

    public Integer getExaminee() {
        return examinee;
    }

    public void setExaminee(Integer examinee) {
        this.examinee = examinee;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
