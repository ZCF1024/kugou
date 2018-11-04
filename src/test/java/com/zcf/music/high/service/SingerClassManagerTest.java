package com.zcf.music.high.service;

import com.zcf.music.high.domain.SingerClass;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/6 10:36
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SingerClassManagerTest {

    @Autowired
    private SingerClassManager singerClassManager;

    @Test
    public void testSave() {
        List<SingerClass> singerClasses = HttpRequestUtil.getSingerClassList();
        this.singerClassManager.save(singerClasses);
    }

    @Test
    public void testGetAllClassId() {
        List<Integer> classIds = this.singerClassManager.getAllClassId();
        System.out.println(classIds.size());
    }
}
