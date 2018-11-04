package com.zcf.music.high.repository;

import com.zcf.music.high.domain.Special;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 20:14
 * @Desc
 */
public interface SpecialRepository extends JpaRepository<Special, Long> {

    @Query(value = "select s.specialId from Special s")
    List<Long> getAllSpecialId();
}
