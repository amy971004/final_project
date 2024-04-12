package org.example.post.dto;

import lombok.Data;

@Data
public class FollowDTO {
    private int followId;
    private String userNickname;
    private String followUserId;
}
