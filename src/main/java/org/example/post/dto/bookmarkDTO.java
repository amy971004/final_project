package org.example.post.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class bookmarkDTO {
    private int bookmarkId;
    private String userNickname;
    private int postId;
    private Date bookmarkTime;
}
