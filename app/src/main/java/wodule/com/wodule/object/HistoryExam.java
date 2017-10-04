package wodule.com.wodule.object;

/**
 * Created by MyPC on 29/06/2017.
 */
public class HistoryExam {
    String date, exam, score;

    public HistoryExam() {
    }

    public HistoryExam(String date, String exam, String score) {
        this.date = date;
        this.exam = exam;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
