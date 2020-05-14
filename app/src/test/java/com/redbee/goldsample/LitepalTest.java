package com.redbee.goldsample;

import com.redbee.goldsample.entity.User;
import com.redbee.goldsample.logger.LoggerUtil;

import org.junit.Test;
import org.litepal.LitePal;

import java.util.List;

public class LitepalTest {
    @Test
    public void SqLiteTest() {
        User jacky = new User("jacky",
                "12345678",
                "18665397331",
                "1039459091@qq.com",
                "JiangXi ji'an");
        jacky.save();
        User update = LitePal.find(User.class, 1);
        LoggerUtil.d("Update user:" + update.toString());
        update.setAddress("GuangZhouing");
        update.save();
        List<User> read = (List<User>) LitePal.where("name=?", "jacky").find(User.class);
        for(User user:read)
        {
            System.out.print("Found user:" + read.toString() + "\n");
        }
        int ret = LitePal.delete(User.class, 1);
    }
}
