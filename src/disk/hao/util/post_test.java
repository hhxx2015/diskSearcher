package disk.hao.util;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class post_test {
    private static CloseableHttpClient httpclient = HttpClients.createDefault();
//    private ProxySet proxyss = new ProxySet();

    public static HttpPost setHead(HttpPost httpPost){
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpPost.setHeader("Cache-Control", "max-age=0");
        httpPost.setHeader("Connection", "keep-alive");
//        httpPost.setHeader("Content-Length", "90");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Cookie", "BAIDUID=13994D557A1C3B4AB779BF013826231B:FG=1; BIDUPSID=13994D557A1C3B4AB779BF013826231B; PSTM=1467091676; REALTIME_TRANS_SWITCH=1; FANYI_WORD_SWITCH=1; BDUSS=0VFanBGTWJNZmlGMGpQdDZ2RzZobEpsZVNTejVESGVlcE53N00zTHhkV085a2RZSVFBQUFBJCQAAAAAAAAAAAEAAAAJqNxDxLPOtNaq08O7pwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI5pIFiOaSBYQ; PSINO=2; H_PS_PSSID=1426_21420_21079_21454_21395_21378_21190_20929; locale=zh; Hm_lvt_64ecd82404c51e03dc91cb9e8c025574=1478520213,1478520543,1478520585,1478520593; Hm_lpvt_64ecd82404c51e03dc91cb9e8c025574=1478520593; hasSeenTips=1; from_lang_often=%5B%7B%22value%22%3A%22en%22%2C%22text%22%3A%22%u82F1%u8BED%22%7D%2C%7B%22value%22%3A%22zh%22%2C%22text%22%3A%22%u4E2D%u6587%22%7D%5D; to_lang_often=%5B%7B%22value%22%3A%22zh%22%2C%22text%22%3A%22%u4E2D%u6587%22%7D%2C%7B%22value%22%3A%22en%22%2C%22text%22%3A%22%u82F1%u8BED%22%7D%5D");
        httpPost.setHeader("Host", "fanyi.baidu.com");
        httpPost.setHeader("Referer", "http://fanyi.baidu.com/?aldtype=16047");
        httpPost.setHeader("Origin", "http://fanyi.baidu.com");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        return httpPost;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://fanyi.baidu.com/v2transapi";
        String message_data = "from=en&to=zh&query=Wonderful+party+and+new+friends+make+everyone+feel&simple_means_flag=3";
        HttpPost httppost = new HttpPost(url);
        httppost = setHead(httppost);
        httppost.setEntity(new StringEntity(message_data));
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String str = EntityUtils.toString(entity);
        System.out.println(str);
    }

}

