package disk.hao.util;

import disk.hao.config.config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Request_util {
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private ProxySet proxyss = new ProxySet();

    public HttpGet setHead(HttpGet httpGet,String uid){
        httpGet.setHeader("Accept", "image/webp,image/*,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "max-age=0");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie", "");
        httpGet.setHeader("Host", "pan.baidu.com");
        httpGet.setHeader("Referer", "https://pan.baidu.com/share/home?uk="+uid+"&view=share");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        return httpGet;
    }

    public static String getString(String tofind, String all) {
        String str = null;
        Pattern p = Pattern.compile(tofind);
        Matcher m = p.matcher(all);
        while (m.find()) {
            str = m.group();
        }
        return str;
    }

    private String getuid(String url){
        String uid = getString("uk=[0-9]{5,15}",url);
        uid = uid.replace("uk=","");
        return uid;
    }

    private String download_url_proxy(String url) throws IOException {
        String uid = getuid(url);
        String proxy_str = proxyss.random_proxy_local();
//        String proxy_str = proxyss.random_proxy();
        RequestConfig http_config =null;
        if(proxy_str==null){
            http_config = RequestConfig.custom()
                    .setConnectTimeout(config.CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(config.CONNECT_READ_TIMEOUT).build();
        }else{
            String ss[] = proxy_str.split(":");
            HttpHost proxy = new HttpHost(ss[0], Integer.parseInt(ss[1].trim()));
            http_config = RequestConfig.custom().setProxy(proxy)
                    .setConnectTimeout(config.CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(config.CONNECT_READ_TIMEOUT).build();
        }

        HttpGet httpget = new HttpGet(url);

        httpget.setConfig(http_config);
        httpget = setHead(httpget,uid);
        HttpEntity entity =null;
        try{
            CloseableHttpResponse response = httpclient.execute(httpget);
            entity = response.getEntity();
        }catch(ConnectTimeoutException e){
            return download_url(url);
        }
        System.out.println(EntityUtils.toString(entity));
        return EntityUtils.toString(entity);
    }


    private String download_url(String url) throws IOException {
        HttpGet httpget = new HttpGet(url);
        String uid = getuid(url);
        httpget = setHead(httpget,uid);
        HttpEntity entity =null;
        try{
            CloseableHttpResponse response = httpclient.execute(httpget);
            entity = response.getEntity();
        }catch(ConnectTimeoutException e){
            return download_url(url);
        }

        return EntityUtils.toString(entity);
    }

    public String download_url_out(String url){
        System.out.println(url);
        try {
            Thread.sleep(config.REQUEST_TIME);
            String s =this.download_url(url);
//            System.out.println(s);
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

//    http://pan.baidu.com/pcloud/user/getinfo?bdstoken=null&query_uk=4201519420&channel=chunlei&clienttype=0&web=1
//    http://pan.baidu.com/pcloud/friend/getfollowlist?query_uk=4201519420&limit=1&start=1&bdstoken=null&channel=chunlei&clienttype=0&web=1
//    http://pan.baidu.com/pcloud/friend/getfanslist?query_uk=4201519420&limit=1&start=1&bdstoken=null&channel=chunlei&clienttype=0&web=1
//    https://yun.baidu.com/pcloud/feed/getsharelist?t=1475065359126&category=0&auth_type=1&request_location=share_home&start=1&limit=6&query_uk=3560277524
//    https://pan.baidu.com/pcloud/feed/getsharelist?t=1477142265745&category=0&auth_type=1&request_location=share_home&start=0&limit=60&query_uk=3678306532&channel=chunlei&clienttype=0&web=1

    public static void main(String[] args) {
        Request_util r = new Request_util();
        String s = "https://pan.baidu.com/pcloud/feed/getsharelist?t=1477142265745&category=0&auth_type=1&request_location=share_home&start=0&limit=60&query_uk=3678306532&channel=chunlei&clienttype=0&web=1";
        String uid = r.getuid(s);
        System.out.println(uid);
    }

}

