package wodule.com.wodule.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MyPC on 06/10/2017.
 */
public class Category {
    @SerializedName("identifier")
    @Expose
    private Integer identifier;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("status")
    @Expose
    private String status;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
