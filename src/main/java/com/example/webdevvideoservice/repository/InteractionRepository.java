package com.example.webdevvideoservice.repository;

import com.example.webdevvideoservice.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    Interaction findByVideoIdAndUserId(Long videoId, Long userId);
}
