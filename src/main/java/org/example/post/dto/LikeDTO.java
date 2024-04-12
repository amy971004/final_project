package org.example.post.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class LikeDTO {
    private int likeId;
    private int postId;
    private String user_nickname;
    private Date liketime;
}
