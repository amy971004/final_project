package org.example.post.dto;

import lombok.Data;

@Data
public class LikePushDTO {
    int like;
    int postId;
    String loginNickname;
}
