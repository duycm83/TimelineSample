package jp.co.spidersoft.timelinesample;

/**
 * Created by lamphucduy on 16/06/26.
 */
public class PostDetail {
    private String title;
    private long time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public PostDetail(String title, long time) {
        this.title = title;
        this.time = time;
    }
}
