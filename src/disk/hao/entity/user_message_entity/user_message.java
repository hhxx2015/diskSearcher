package disk.hao.entity.user_message_entity;

/**
 * Created by hao on 16-10-23.
 */
public class user_message {
    private user_info user_info;

    public user_message(disk.hao.entity.user_message_entity.user_info user_info) {
        this.user_info = user_info;
    }

    public disk.hao.entity.user_message_entity.user_info getUser_info() {
        return user_info;
    }

    public void setUser_info(disk.hao.entity.user_message_entity.user_info user_info) {
        this.user_info = user_info;
    }
}
