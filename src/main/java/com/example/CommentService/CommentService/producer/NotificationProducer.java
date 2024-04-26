package com.example.CommentService.CommentService.producer;



import com.example.CommentService.CommentService.Models.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {
    private KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    public void sendNotification(String topic, NotificationDTO notification) {
        kafkaTemplate.send(topic, notification);
    }
}
