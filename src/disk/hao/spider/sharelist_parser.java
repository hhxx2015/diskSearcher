package disk.hao.spider;

import disk.hao.config.config;
import disk.hao.entity.user_message_entity.user_info;
import disk.hao.entity.user_message_entity.user_message;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hao on 16-10-22.
 *
 http://pan.baidu.com/pcloud/user/getinfo?bdstoken=null&query_uk=4201519420&channel=chunlei&clienttype=0&web=1
 http://pan.baidu.com/pcloud/friend/getfollowlist?query_uk=4201519420&limit=1&start=1&bdstoken=null&channel=chunlei&clienttype=0&web=1
 http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=4201519420&limit=1&start=1&bdstoken=null&channel=chunlei&clienttype=0&web=1
 https://yun.baidu.com/pcloud/feed/getsharelist?t=1475065359126&category=0&auth_type=1&request_location=share_home&start=1&limit=6&query_uk=3560277524
 https://pan.baidu.com/pcloud/feed/getsharelist?t=1477142265745&category=0&auth_type=1&request_location=share_home&start=0&limit=60&query_uk=3678306532&channel=chunlei&clienttype=0&web=1
 *
 */

public class sharelist_parser implements Parser{

    private int limit = 60;

    public ArrayList<String> get_urls(String userid){
        ArrayList<String> share_urls = new ArrayList<>();
        try{
            String url_message= linkFormat.url_message_format(config.message_link,userid);
            String umess_string = requestUtil.download_url_out(url_message);
            user_info umess = gson.fromJson(umess_string,user_message.class).getUser_info();
            for(int i = 0;i<(umess.getPubshare_count());i+=limit){
                String url_share = linkFormat.url_share_format(config.share_link,userid,limit,i);
                share_urls.add(url_share);
            }
        }catch (IOException e){

        }
        return share_urls;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        sharelist_parser sharelistSpider = new sharelist_parser();
        ArrayList<String> share_urls = sharelistSpider.get_urls("3678306532");
        System.out.println(share_urls.get(0));
    }


}
