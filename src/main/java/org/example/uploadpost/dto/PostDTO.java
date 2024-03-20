package org.example.uploadpost.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PostDTO {
    private int postId;
    private String userId;
    private String content;
    private Date uploadDate;
}
