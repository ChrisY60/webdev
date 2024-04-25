package com.example.webdevnotificationservice.mapper;

import com.example.webdevnotificationservice.DTO.NotificationDTO;
import com.example.webdevnotificationservice.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper NOTIFICATION_MAPPER = Mappers.getMapper(NotificationMapper.class);

    Notification fromNotificationDTO(NotificationDTO notificationDTO);
    NotificationDTO toNotificationDTO(Notification notification);
}
