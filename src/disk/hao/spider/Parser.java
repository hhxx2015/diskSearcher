package disk.hao.spider;

import com.google.gson.Gson;
import disk.hao.util.Request_util;
import disk.hao.util.link_format;

import java.util.ArrayList;

/**
 * Created by hao on 16-10-23.
 */
public interface Parser {
    Request_util requestUtil = new Request_util();
    Gson gson = new Gson();
    link_format linkFormat= new link_format();
    int limit = 60;

    public ArrayList<String> get_urls(String userid);
}
