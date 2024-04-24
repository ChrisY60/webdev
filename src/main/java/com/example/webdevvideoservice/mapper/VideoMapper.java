package com.example.webdevvideoservice.mapper;

import com.example.webdevvideoservice.DTO.VideoListDTO;
import com.example.webdevvideoservice.DTO.VideoUploadDTO;
import com.example.webdevvideoservice.DTO.VideoViewDTO;
import com.example.webdevvideoservice.entity.Video;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VideoMapper {
    VideoMapper VIDEO_MAPPER = Mappers.getMapper(VideoMapper.class);

    VideoListDTO toListingDTO(Video video);
    List<VideoListDTO> toListingDTOs(List<Video> videos);

    Video fromUploadDTO(VideoUploadDTO videoDTO);
    VideoUploadDTO toUploadDTO(Video video);

    VideoViewDTO toViewDTO(Video video);
}
