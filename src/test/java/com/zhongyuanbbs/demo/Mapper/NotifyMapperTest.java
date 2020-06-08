package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.DemoApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NotifyMapperTest extends DemoApplicationTests {

    @Autowired
    private NotifyMapper notifyMapper;

    @Test
    public void test(){
        int count = notifyMapper.queryNotifyCountByUserID(1);
        System.out.println(count);
    }

}
