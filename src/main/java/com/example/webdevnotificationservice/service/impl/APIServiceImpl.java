package com.example.webdevnotificationservice.service.impl;

import com.example.webdevnotificationservice.service.APIService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class APIServiceImpl implements APIService {
    private final WebClient webClient;

    public APIServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public String getUsername(Long userId) {
        String endpoint = "http://localhost:8080/users/" + userId + "/username";

        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String getUserEmail(Long userId) {
        String endpoint = "http://localhost:8080/users/" + userId + "/email";

        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String getVideoTitle(Long videoId) {
        String endpoint = "http://localhost:8080/videos/" + videoId + "/title";

        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
