package com.example.CommentService.CommentService.Repositories;

import com.example.CommentService.CommentService.Models.Comment;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface CommentRepository extends CassandraRepository<Comment, Long> {
    List<Comment> getCommentsByVideoId(long videoId);
}
