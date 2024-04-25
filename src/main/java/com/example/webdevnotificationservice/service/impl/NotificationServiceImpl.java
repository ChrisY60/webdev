package com.example.webdevnotificationservice.service.impl;

import com.example.webdevnotificationservice.DTO.NotificationDTO;
import com.example.webdevnotificationservice.entity.Notification;
import com.example.webdevnotificationservice.enums.NotificationType;
import com.example.webdevnotificationservice.service.EmailService;
import com.example.webdevnotificationservice.service.NotificationService;
import com.example.webdevnotificationservice.service.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.webdevnotificationservice.mapper.NotificationMapper.NOTIFICATION_MAPPER;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final EmailService emailService;
    private final APIService APIService;

    @Override
    public void sendVideoUploadNotification(NotificationDTO notificationDTO) {
        Notification notification = NOTIFICATION_MAPPER.fromNotificationDTO(notificationDTO);
        notification.setType(NotificationType.VIDEO_UPLOAD);

        emailService.sendEmail(
                APIService.getUserEmail(notificationDTO.getReceiverId()),
                "Video Upload Notification",
                notificationDTO.getMessage() + " User " + APIService.getUsername(notificationDTO.getSenderId()) +
                        " uploaded a new video titled " + APIService.getVideoTitle(notificationDTO.getVideoId()) +
                        "."
        );
    }

    @Override
    public void sendVideoLikeNotification(NotificationDTO notificationDTO) {
        Notification notification = NOTIFICATION_MAPPER.fromNotificationDTO(notificationDTO);
        notification.setType(NotificationType.VIDEO_LIKE);

        emailService.sendEmail(
                APIService.getUserEmail(notificationDTO.getReceiverId()),
                "Video Like Notification",
                notificationDTO.getMessage() + " User " + APIService.getUsername(notificationDTO.getSenderId()) +
                        " liked your video titled " + APIService.getVideoTitle(notificationDTO.getVideoId()) +
                        "."
        );
    }
}
