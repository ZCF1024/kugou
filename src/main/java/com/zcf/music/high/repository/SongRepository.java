package com.zcf.music.high.repository;

import com.zcf.music.high.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author zcf
 * @Create 2018/8/5 20:14
 * @Desc
 */
public interface SongRepository extends JpaRepository<Song, String> {
}
