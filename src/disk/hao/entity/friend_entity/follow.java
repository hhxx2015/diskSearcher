package disk.hao.entity.friend_entity;

/**
 * Created by hao on 16-10-23.
 */
public class follow {
    private String follow_uname;
    private String intro;
    private int follow_count;
    private int fans_count;
    private int pubshare_count;
    private long follow_uk;

    public follow(String follow_uname, String intro, int fans_count, int follow_count, int pubshare_count, long follow_uk) {
        this.follow_uname = follow_uname;
        this.intro = intro;
        this.fans_count = fans_count;
        this.follow_count = follow_count;
        this.pubshare_count = pubshare_count;
        this.follow_uk = follow_uk;
    }

    public String getFollow_uname() {
        return follow_uname;
    }

    public void setFollow_uname(String follow_uname) {
        this.follow_uname = follow_uname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public int getPubshare_count() {
        return pubshare_count;
    }

    public void setPubshare_count(int pubshare_count) {
        this.pubshare_count = pubshare_count;
    }

    public long getFollow_uk() {
        return follow_uk;
    }

    public void setFollow_uk(long follow_uk) {
        this.follow_uk = follow_uk;
    }
}
