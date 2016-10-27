//package disk.hao.spider;
//
//import com.google.gson.Gson;
//import disk.hao.config.config;
//import disk.hao.entity.friend_entity.fans;
//import disk.hao.entity.friend_entity.fans_list;
//import disk.hao.entity.friend_entity.follow;
//import disk.hao.entity.friend_entity.follow_list;
//import disk.hao.entity.share_entity.shareList;
//import disk.hao.entity.share_entity.share_message;
//import disk.hao.util.Request_util;
//import disk.hao.util.SaveShareMessage;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by hao on 16-10-23.
// *
// */
//public class spider_main {
//    private fans_parser fansParser = new fans_parser();
//    private follow_parser followParser = new follow_parser();
//    private sharelist_parser sharelistParser= new sharelist_parser();
//    private Request_util requestUtil = new Request_util();
//    private Gson gson = new Gson();
//    private SaveShareMessage saveShareMessage= new SaveShareMessage();
////    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(config.THREAD_NUB);
//
//    private HashSet<String> uidset = new HashSet<>();
//
//    public spider_main(){
//
//    }
//
//    public void fansUidParser(fans f,String uid,int deep)
//            throws IOException, InterruptedException {
//        if(uidset.contains(uid)){
//
//        }else{
//            uidset.add(uid);
//            if(f.getPubshare_count()>3){
//                recursion_parser(uid,deep);
//            }
//        }
//    }
//
//    public void followUidParser(follow f,String uid,int deep) throws IOException, InterruptedException {
//        if(uidset.contains(uid)){
//
//        }else{
//            uidset.add(uid);
//            if(f.getPubshare_count()>3){
//                recursion_parser(uid,deep);
//            }
//        }
//    }
//
//
//    public boolean recursion_parser (String start_uid,int deep)
//            throws IOException, InterruptedException {
//        deep++;
//        if(deep>config.SPIDER_DEEP){
//            return false;
//        }
//
//        cachedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
//                            config.disk_message_path + start_uid+".txt")));
//                    bw.write(start_uid+"\n");
//                    downLoad_share_short_link(start_uid,bw);
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e2) {
//                    e2.printStackTrace();
//                }
//
//            }
//        });
//
//
//        ArrayList<String> fans_urls = fansParser.get_urls(start_uid);
//
//
//        for (String fansurl : fans_urls){
//            String fans_json = requestUtil.download_url_out(fansurl);
////            System.out.println(fans_json);
//            List<fans> fans_records = gson.fromJson(fans_json,fans_list.class).getRecords();
//            for(fans f:fans_records){
//                String uid = ""+f.getFans_uk();
//                fansUidParser(f,uid,deep);
//            }
//        }
//
//        ArrayList<String> follow_urls = followParser.get_urls(start_uid);
//        for (String followurl : follow_urls){
//            String follow_json = requestUtil.download_url_out(followurl);
//            List<follow> follow_records = gson.fromJson(follow_json,follow_list.class).getRecords();
//            for(follow f:follow_records){
//                String uid = ""+f.getFollow_uk();
//                followUidParser(f,uid,deep);
//            }
//        }
//        return true;
//    }
//
//
//    public void downLoad_share_short_link(String uid,BufferedWriter bw) throws IOException, InterruptedException {
//
//        ArrayList<String> share_urls = sharelistParser.get_urls(uid);
//        for(String shareUrl : share_urls){
//            Thread.sleep(config.DOWNLOAD_REQUEST_TIME);
//            String share_String = requestUtil.download_url_out(shareUrl);
//            List<share_message> share_list= gson.fromJson(share_String,shareList.class).getRecords();
//            try {
//                for (share_message shareMessage : share_list) {
//                    saveShareMessage.save(shareMessage, bw);
//                }
//            }catch(NullPointerException e){
//                System.out.println(share_String);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        spider_main spiderMain = new spider_main();
//        spiderMain.recursion_parser("3678306532",0);
//    }
//
//}
