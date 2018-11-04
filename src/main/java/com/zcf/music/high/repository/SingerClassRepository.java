package com.zcf.music.high.repository;

import com.zcf.music.high.domain.SingerClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 20:13
 * @Desc
 */
public interface SingerClassRepository extends JpaRepository<SingerClass, Integer> {

    @Query(value = "select class_id from singer_class", nativeQuery = true)
    List<Integer> getAllClassId();

}
