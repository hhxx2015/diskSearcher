package disk.hao.entity.friend_entity;

/**
 * Created by hao on 16-10-23.
 */
public class fans {
    private String fans_uname;
    private String intro;
    private int follow_count;
    private int fans_count;
    private int pubshare_count;
    private long fans_uk;

    public fans(String fans_uname, String intro, int follow_count, int fans_count, int pubshare_count, long fans_uk) {
        this.fans_uname = fans_uname;
        this.intro = intro;
        this.follow_count = follow_count;
        this.fans_count = fans_count;
        this.pubshare_count = pubshare_count;
        this.fans_uk = fans_uk;
    }

    public String getFans_uname() {
        return fans_uname;
    }

    public void setFans_uname(String fans_uname) {
        this.fans_uname = fans_uname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getFollow_count() {
        return follow_count;
    }

    public void setFollow_count(int follow_count) {
        this.follow_count = follow_count;
    }

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getPubshare_count() {
        return pubshare_count;
    }

    public void setPubshare_count(int pubshare_count) {
        this.pubshare_count = pubshare_count;
    }

    public long getFans_uk() {
        return fans_uk;
    }

    public void setFans_uk(long fans_uk) {
        this.fans_uk = fans_uk;
    }
}
