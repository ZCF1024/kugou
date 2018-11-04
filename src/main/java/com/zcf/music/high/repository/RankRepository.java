package com.zcf.music.high.repository;

import com.zcf.music.high.domain.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/4 23:12
 * @Desc
 */
public interface RankRepository extends JpaRepository<Rank, Integer> {

    @Query(value = "select rank_id from rank ", nativeQuery = true)
    List<Integer> getAllRankId();

}
