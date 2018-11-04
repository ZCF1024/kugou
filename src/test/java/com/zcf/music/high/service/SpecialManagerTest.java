package com.zcf.music.high.service;

import com.zcf.music.high.domain.Special;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 23:12
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialManagerTest {

    @Autowired
    private SpecialManager specialManager;

    @Test
    public void testSave() {
        List<Special> specials = HttpRequestUtil.getSpecialList();
        this.specialManager.save(specials);
    }

    @Test
    public void testgetAllSpecialId() {
        List<Long> ids = this.specialManager.getAllSpecialId();
        System.out.println(ids.size());
    }
}
