package com.example.webdevvideoservice.service.impl;

import com.example.webdevvideoservice.DTO.NotificationDTO;
import com.example.webdevvideoservice.DTO.VideoListDTO;
import com.example.webdevvideoservice.DTO.VideoUploadDTO;
import com.example.webdevvideoservice.DTO.VideoViewDTO;
import com.example.webdevvideoservice.entity.Video;
import com.example.webdevvideoservice.entity.Interaction;
import com.example.webdevvideoservice.producer.NotificationProducer;
import com.example.webdevvideoservice.repository.InteractionRepository;
import com.example.webdevvideoservice.repository.VideoRepository;
import com.example.webdevvideoservice.service.StorageService;
import com.example.webdevvideoservice.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.webdevvideoservice.enums.InteractionType;

import java.util.List;
import java.util.UUID;

import static com.example.webdevvideoservice.mapper.VideoMapper.VIDEO_MAPPER;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final InteractionRepository interactionRepository;
    private final StorageService storageService;
    private final NotificationProducer notificationProducer;

    @Value("${video.like.topic}")
    private String likeTopic;

    @Override
    @Cacheable(value = "videos", key = "#userId")
    public List<VideoListDTO> getVideosByUserId(Long userId) {
        return VIDEO_MAPPER.toListingDTOs(videoRepository.findByUserId(userId));
    }

    @Override
    @Cacheable(value = "videos", key = "#title + #page + #size")
    public List<VideoListDTO> getVideosByTitle(String title, Integer page, Integer size) {
        return VIDEO_MAPPER.toListingDTOs(videoRepository.findByTitleContaining(title, PageRequest.of(page, size)));
    }

    @Override
    public VideoUploadDTO uploadVideo(MultipartFile videoFile, VideoUploadDTO videoUploadDTO) {
        String videoUrl = storageService.storeVideo(
                videoFile,
                videoUploadDTO.getUserId() + "-" + UUID.randomUUID() + ".mp4"
        );
        String thumbnailUrl = UUID.randomUUID() + ".png";

        Video video = VIDEO_MAPPER.fromUploadDTO(videoUploadDTO);
        video.setUrl(videoUrl);
        video.setThumbnailUrl(thumbnailUrl);

        videoRepository.save(video);

        return VIDEO_MAPPER.toUploadDTO(video);
    }

    @Override
    public VideoUploadDTO uploadTest(VideoUploadDTO videoUploadDTO) {
        Video video = VIDEO_MAPPER.fromUploadDTO(videoUploadDTO);
        videoRepository.save(video);

        return VIDEO_MAPPER.toUploadDTO(video);
    }

    @Override
    @Cacheable(value = "videos", key = "#videoId")
    public VideoViewDTO getVideoById(Long videoId, Long userId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found"));

        if (interactionRepository.findByVideoIdAndUserId(videoId, userId) == null) {
            Interaction interaction = new Interaction();
            interaction.setUserId(userId);
            interaction.setVideoId(videoId);
            interaction.setType(InteractionType.VIEW);
            interactionRepository.save(interaction);
        }

        video.setViews(video.getViews() + 1);
        videoRepository.save(video);
        return VIDEO_MAPPER.toViewDTO(video);
    }

    @Override
    public void interactWithVideo(Long videoId, Long userId, String interactionType) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found"));

        Interaction interaction = interactionRepository.findByVideoIdAndUserId(videoId, userId);
        InteractionType previousType = interaction.getType();
        interactionRepository.delete(interaction);

        interaction.setType(InteractionType.valueOf(interactionType));
        interactionRepository.save(interaction);

        switch (InteractionType.valueOf(interactionType)) {
            case LIKE:
                likeVideo(video, userId);
                break;
            case DISLIKE:
                dislikeVideo(video);
                break;
        }

        switch (previousType) {
            case LIKE:
                removeLike(video);
                break;
            case DISLIKE:
                removeDislike(video);
                break;
        }
    }

    private void likeVideo(Video video, Long userId) {
        video.setLikes(video.getLikes() + 1);
        videoRepository.save(video);

        NotificationDTO notificationDTO = new NotificationDTO(
                video.getUserId(), userId,"New like on your video!"
        );
        notificationDTO.setVideoId(video.getId());
        notificationProducer.sendNotification(likeTopic, notificationDTO);
    }

    private void dislikeVideo(Video video) {
        video.setDislikes(video.getDislikes() + 1);
        videoRepository.save(video);
    }

    private void removeLike(Video video) {
        video.setLikes(video.getLikes() - 1);
        videoRepository.save(video);
    }

    private void removeDislike(Video video) {
        video.setDislikes(video.getDislikes() - 1);
        videoRepository.save(video);
    }
}
