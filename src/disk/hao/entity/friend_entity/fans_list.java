package disk.hao.entity.friend_entity;


import java.util.List;

/**
 * Created by hao on 16-10-23.
 */
public class fans_list {
    private List<fans> fans_list;

    public fans_list(List<fans> fans_list) {
        this.fans_list = fans_list;
    }

    public List<fans> getRecords() {
        return fans_list;
    }

    public void setRecords(List<fans> fans_list) {
        this.fans_list = fans_list;
    }
}
