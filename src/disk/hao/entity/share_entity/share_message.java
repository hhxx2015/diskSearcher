package disk.hao.entity.share_entity;

/**
 * Created by hao on 16-10-23.
 */
public class share_message {
    private String username;
    private String title;
    private String shorturl;

    public share_message(String username, String title, String shorturl) {
        this.username = username;
        this.title = title;
        this.shorturl = shorturl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
