package com.example.CommentService.CommentService.Controllers;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.CommentService.CommentService.Models.CommentDTO;
import com.example.CommentService.CommentService.Models.Comment;
import com.example.CommentService.CommentService.Models.NotificationDTO;
import com.example.CommentService.CommentService.Repositories.CommentRepository;
import com.example.CommentService.CommentService.producer.NotificationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final NotificationProducer notificationProducer;

    @Autowired
    public CommentController(CommentRepository commentRepository, NotificationProducer notificationProducer) {
        this.commentRepository = commentRepository;
        this.notificationProducer = notificationProducer;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        commentDTO.setTimestamp(new Date());

        Comment comment = new Comment();
        UUID commentId = Uuids.timeBased();
        comment.setId(commentId);
        comment.setAuthorId(commentDTO.getAuthorId());
        comment.setVideoId(commentDTO.getVideoId());
        comment.setContent(commentDTO.getContent());
        comment.setTimestamp(commentDTO.getTimestamp());

        Comment savedComment = commentRepository.save(comment);

        CommentDTO savedCommentDTO = new CommentDTO();
        savedCommentDTO.setAuthorId(savedComment.getAuthorId());
        savedCommentDTO.setVideoId(savedComment.getVideoId());
        savedCommentDTO.setContent(savedComment.getContent());
        savedCommentDTO.setTimestamp(savedComment.getTimestamp());
        NotificationDTO notificationDTO =new NotificationDTO(comment.getAuthorId(), comment.getVideoId(), comment.getId(), "New Comment Added!" );
        notificationProducer.sendNotification("user-subscribe", notificationDTO);
        return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByVideoId(@PathVariable long videoId) {
        List<Comment> comments = commentRepository.getCommentsByVideoId(videoId);

        List<CommentDTO> commentDTOs = comments.stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    dto.setAuthorId(comment.getAuthorId());
                    dto.setVideoId(comment.getVideoId());
                    dto.setContent(comment.getContent());
                    dto.setTimestamp(comment.getTimestamp());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(commentDTOs);
    }
}
