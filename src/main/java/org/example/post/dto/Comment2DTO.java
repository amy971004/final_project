package org.example.post.dto;

import lombok.Data;

@Data
public class Comment2DTO {
    private String comment;
    private int postId;
    private String loginNickname;
}
