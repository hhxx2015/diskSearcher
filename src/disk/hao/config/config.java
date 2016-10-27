package disk.hao.config;

/**
 * Created by hao on 16-10-23.
 */
public class config {
    public final static String message_link = "http://pan.baidu.com/pcloud/user/getinfo?bdstoken=null&query_uk=%s&channel=chunlei&clienttype=0&web=1";
    public final static String follow_link = "http://pan.baidu.com/pcloud/friend/getfollowlist?query_uk=%s&limit=%d&start=%d";
    public final static String fans_link = "http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=%s&limit=%d&start=%d";
    public final static String share_link = "https://yun.baidu.com/pcloud/feed/getsharelist?t=1475065359126&category=0&auth_type=1&request_location=share_home&start=%d&limit=%d&query_uk=%s";

    public final static String PROXY_path = "/home/hao/PycharmProjects/ipproxy/data/";

    public final static String disk_message_path = "/home/hao/文档/j2ee_workspace/diskSearcher/data/user_share/";

    public final static int CONNECT_TIMEOUT = 6000;
    public final static int CONNECT_READ_TIMEOUT = 6000;
    public final static int SORCKET_TIMEOUT = 6000;

    public final static int REQUEST_TIME = 5000;
    public final static int DOWNLOAD_REQUEST_TIME = 8000;

    public final static int THREAD_NUB = 2;
    public final static int SPIDER_DEEP = 8;

}
