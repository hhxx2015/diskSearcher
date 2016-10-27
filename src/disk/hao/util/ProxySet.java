package disk.hao.util;

import disk.hao.config.config;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hao on 16-10-24.
 */
public class ProxySet {



    private ArrayList<String> peoxys_str = new ArrayList<>();
    private Random r_index;

    public ProxySet(){
        try {
            File[] fs = new File(config.PROXY_path).listFiles();
            for(File f:fs){
                if(f.getName().startsWith("RE")){

                }else{
                    BufferedReader br = new BufferedReader(new FileReader(f));
                    String line = "";
                    while((line = br.readLine())!=null){
                        peoxys_str.add(line);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.r_index = new Random();
    }


    public String random_proxy_local(){
        int host_id = r_index.nextInt(peoxys_str.size());
        return peoxys_str.get(host_id).split("\t")[0];
    }
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private Random r = new Random();
    public String random_proxy() throws IOException {
        String proxyurl = "http://127.0.0.1:8000/?count=20&types=0";

        HttpGet httpget = new HttpGet(proxyurl);
        CloseableHttpResponse response = httpclient.execute(httpget);
        RequestConfig http_config = RequestConfig.custom()
                .setConnectTimeout(config.CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(config.CONNECT_READ_TIMEOUT)
                .setSocketTimeout(config.SORCKET_TIMEOUT)
                .build();
        httpget.setConfig(http_config);
        HttpEntity entity = response.getEntity();
        String proxy_str="";
        try{
            proxy_str =  EntityUtils.toString(entity);
            String proxs[] = proxy_str.split("\n");
            int pindex = r.nextInt(proxs.length);
            proxy_str = proxs[pindex];
            proxy_str = proxy_str.substring(3).replace("\'","").replace(")","");

        }catch(ConnectTimeoutException e){
            proxy_str = null;
        }

        return proxy_str;
    }

    public static void main(String[] args) throws IOException {

        ProxySet pros = new ProxySet();
        System.out.println(pros.random_proxy_local());

    }


}
