package org.example.post.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private int postId;
    private int imageNo;
    private String fileName;
    private String filePath;
}
