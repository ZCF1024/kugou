package com.zcf.music.high.service;

import com.zcf.music.high.domain.Singer;
import com.zcf.music.high.domain.SingerClass;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 17:01
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SingerServiceTest {

    @Autowired
    private SingerManager singerManager;

    @Test
    public void testSave() {
        List<SingerClass> singerClasses = HttpRequestUtil.getSingerClassList();
        for (SingerClass singerClass : singerClasses) {
            List<Singer> singers = HttpRequestUtil.getSingerListOfClass(singerClass.getClassId());
            System.out.println("=======================================================");
            System.out.println("classId: " + singerClass.getClassId() + singers.size() + "条 finished......");
            System.out.println("=======================================================");
            this.singerManager.save(singers);
        }

    }
}
