package org.example.post.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentDTO {
    private String user_Nickname;
    private int postId;
    private int level;
    private int articleNo;
    private int parentNo;
    private String postComment;
    private Date writeDate;


}
