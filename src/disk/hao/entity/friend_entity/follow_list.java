package disk.hao.entity.friend_entity;


import java.util.List;

/**
 * Created by hao on 16-10-23.
 */
public class follow_list {
    private List<follow> follow_list;

    public follow_list(List<follow> follow_list) {
        this.follow_list = follow_list;
    }

    public List<follow> getRecords() {
        return follow_list;
    }

    public void setRecords(List<follow> follow_list) {
        this.follow_list = follow_list;
    }
}
