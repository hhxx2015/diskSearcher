package disk.hao.util;

import disk.hao.entity.share_entity.share_message;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by hao on 16-10-23.
 *
 */
public class SaveShareMessage {
    public void save(share_message shareMessage , BufferedWriter bw) throws IOException {
//        System.out.println(shareMessage.getShorturl());
//        System.out.println(shareMessage.getServer_filename());
//        System.out.println(shareMessage.getUsername());
        bw.write(shareMessage.getShorturl()+"\t");
        bw.write(shareMessage.getTitle()+"\t");
        bw.write(shareMessage.getUsername()+"\t");
        bw.newLine();
    }

}
