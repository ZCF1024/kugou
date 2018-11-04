package com.zcf.music.high.service;

import com.zcf.music.high.domain.MV;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/7 11:25
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MVManagerTest {

    @Autowired
    private MVManager mvManager;

    @Test
    public void testSave() {
        List<String> hashs = HttpRequestUtil.searchMV("海阔天空");
        List<MV> mvs = HttpRequestUtil.getMVByHash(hashs);
        System.out.println("size: " + mvs.size());
        this.mvManager.save(mvs);
    }


}
