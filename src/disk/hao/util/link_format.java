package disk.hao.util;

import java.io.IOException;

/**
 * Created by hao on 16-10-23.
 */
public class link_format {

    /**
     * @param url_uf
     * @param userid
     * @return
     * @throws IOException
     */
    public String url_message_format(String url_uf , String userid) throws IOException {
        return String.format(url_uf,userid);
    }

    /**
     *
     * @param url_uf
     * @param userid
     * @param limit
     * @param start
     * @return
     * @throws IOException
     */
    public String url_user_format(String url_uf , String userid, int limit,int start ) throws IOException {
        return String.format(url_uf,userid,limit,start);
    }

    /**
     * @param url_uf
     * @param userid
     * @param limit
     * @param start
     * @return
     * @throws IOException
     */
    public String url_share_format(String url_uf , String userid, int limit,int start ) throws IOException {
        return String.format(url_uf,start,limit,userid);
    }

}
