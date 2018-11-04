package com.zcf.music.high.repository;

import com.zcf.music.high.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 16:55
 * @Desc
 */
public interface SingerRepository extends JpaRepository<Singer, Long> {

    @Query(value = "select s.singerId from Singer s")
    List<Long> getAllSingerId();
}
