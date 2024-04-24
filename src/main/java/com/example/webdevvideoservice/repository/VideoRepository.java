package com.example.webdevvideoservice.repository;

import com.example.webdevvideoservice.entity.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByUserId(Long userId);
    List<Video> findByTitleContaining(String title, Pageable pageable);
}
