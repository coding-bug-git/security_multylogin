package cn.bug.demo01.fakedata;

import cn.bug.demo01.pojo.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author coding-bug
 * date 2022/3/6 9:21
 */

@Data
public class FakeUserList {


    public static List<User> ddLoginUserList = List.of(
            new User("admin", "123456", "admin01"),
            new User("acc01", "123456", "acc01"),
            new User("acc02", "123456", "acc02")
    );

    public static User getUserByDingId(String dingId) {
        List<User> list = ddLoginUserList.stream().filter(
                user -> dingId.equals(user.getDingId())).collect(Collectors.toList());
        return list.get(0);
    }

    public static User getUserByUsernamePassword(String username, String password) {
        List<User> list = ddLoginUserList.stream().filter(
                user -> username.equals(user.getUsername()) && password.equals(user.getPassword())
        ).collect(Collectors.toList());
        return list.get(0);
    }
}
