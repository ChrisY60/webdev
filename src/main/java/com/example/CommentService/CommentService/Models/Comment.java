package com.example.CommentService.CommentService.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @PrimaryKey
    private UUID id;
    private String authorId;
    private long videoId;
    private String content;
    private Date timestamp;
}
