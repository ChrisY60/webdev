package com.example.CommentService.CommentService.Models;

import lombok.Data;
import java.util.Date;

@Data
public class CommentDTO {
    private long authorId;
    private long videoId;
    private String content;
    private Date timestamp;
}
