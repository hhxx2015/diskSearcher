package disk.hao.entity.user_message_entity;

/**
 * Created by hao on 16-10-23.
 */
public class user_info {
    private int fans_count;
    private int follow_count;
    private String intro;
    private String uname;
    private int pubshare_count;

    public user_info(int fans_count, int follow_count, String intro, String uname, int pubshare_count) {
        this.fans_count = fans_count;
        this.follow_count = follow_count;
        this.intro = intro;
        this.uname = uname;
        this.pubshare_count = pubshare_count;
    }

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(int follow_count) {
        this.follow_count = follow_count;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getPubshare_count() {
        return pubshare_count;
    }

    public void setPubshare_count(int pubshare_count) {
        this.pubshare_count = pubshare_count;
    }
}
