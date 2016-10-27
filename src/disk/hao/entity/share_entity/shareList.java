package disk.hao.entity.share_entity;

import java.util.List;

/**
 * Created by hao on 16-10-23.
 */
public class shareList {

    private List<share_message> records;

    public shareList(List<share_message> records) {
        this.records = records;
    }

    public List<share_message> getRecords() {
        return records;
    }

    public void setRecords(List<share_message> records) {
        this.records = records;
    }
}
