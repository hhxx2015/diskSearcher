package disk.hao.spider;

import com.google.gson.Gson;
import disk.hao.config.config;
import disk.hao.entity.friend_entity.fans;
import disk.hao.entity.friend_entity.fans_list;
import disk.hao.entity.friend_entity.follow;
import disk.hao.entity.friend_entity.follow_list;
import disk.hao.entity.share_entity.shareList;
import disk.hao.entity.share_entity.share_message;
import disk.hao.util.Request_util;
import disk.hao.util.SaveShareMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hao on 16-10-23.
 *
 */
public class spider_main2 {
    private fans_parser fansParser = new fans_parser();
    private follow_parser followParser = new follow_parser();
    private sharelist_parser sharelistParser= new sharelist_parser();
    private Request_util requestUtil = new Request_util();
    private Gson gson = new Gson();
    private SaveShareMessage saveShareMessage= new SaveShareMessage();
//    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(config.THREAD_NUB);

    private HashSet<String> uidset = new HashSet<>();

    public spider_main2(){

        File fs[] = new File(config.disk_message_path).listFiles();
        for(File f:fs){
            uidset.add(f.getName().replace(".txt",""));
        }

    }


    public void fansUidParser(List<fans> fans_list,int deep)
            throws IOException, InterruptedException {
        if(fans_list!=null){
            if(fans_list.size()>0){
                for(fans f:fans_list){
                    recursion_parser(""+f.getFans_uk(),deep,f.getFans_count()
                            ,f.getFollow_count(),f.getPubshare_count());
                }
            }
        }
    }


    public void followUidParser(List<follow> follow_list,int deep)
            throws IOException, InterruptedException {
        if(follow_list!=null){
            if(follow_list.size()>0) {
                for (follow f : follow_list) {
                    recursion_parser("" + f.getFollow_uk(), deep, f.getFans_count()
                            , f.getFollow_count(), f.getPubshare_count());
                }
            }
        }

    }

    public boolean recursion_parser (String uid,int deep
                ,int fans,int follow,int share){
        deep++;
        if(deep>config.SPIDER_DEEP){
            return false;
        }
        if(uidset.contains(uid)){
            System.err.println("contain id "+uid);
            return false;
        }
        System.out.println(""+deep);
        uidset.add(uid);

        if(share>5){
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                                config.disk_message_path + uid+".txt")));
                        bw.write(uid+"\n");
                        downLoad_share_short_link(uid,bw);
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }

                }
            });
        }


        if(fans>5){
            ArrayList<String> fans_urls = fansParser.get_urls(uid);
            for (String fansurl : fans_urls){
                String fans_json = requestUtil.download_url_out(fansurl);
                List<fans> fans_records = gson.fromJson(fans_json,fans_list.class).getRecords();
                try {
                    fansUidParser(fans_records,deep);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch(NullPointerException e ){
                    e.printStackTrace();
                }
            }
        }
        if(follow>5){
            ArrayList<String> follow_urls = followParser.get_urls(uid);
            for (String followurl : follow_urls){
                String follow_json = requestUtil.download_url_out(followurl);
                List<follow> follow_records = gson.fromJson(follow_json,follow_list.class).getRecords();
                try {
                    followUidParser(follow_records,deep);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (NullPointerException e ) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public void downLoad_share_short_link(String uid,BufferedWriter bw)
            throws IOException, InterruptedException {
        ArrayList<String> share_urls = sharelistParser.get_urls(uid);
        for(String shareUrl : share_urls){
            Thread.sleep(config.DOWNLOAD_REQUEST_TIME);
            String share_String = requestUtil.download_url_out(shareUrl);
            List<share_message> share_list= gson.fromJson(share_String,shareList.class).getRecords();
            try {
                for (share_message shareMessage : share_list) {
                    saveShareMessage.save(shareMessage, bw);
                }
            }catch(NullPointerException e){
                System.out.println(share_String);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        spider_main2 spiderMain = new spider_main2();
        spiderMain.recursion_parser("1381788159",0,61,7,131);
    }

}
