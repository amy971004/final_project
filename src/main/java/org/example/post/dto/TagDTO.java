package org.example.post.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private int PostId;
    private List<String> hashTag;
}
