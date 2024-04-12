package org.example.post.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PostDTO {
    private int postId;
    private String userNickname;
    private String content;
    private Date uploadDate;
    private String date;

}
