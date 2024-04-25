package com.example.webdevnotificationservice.service;

public interface APIService {
    String getUsername(Long userId);
    String getUserEmail(Long userId);
    String getVideoTitle(Long videoId);
}
