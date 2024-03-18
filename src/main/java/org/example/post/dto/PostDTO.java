package org.example.post.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    private Long id;
    private String content;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private Date uploadTime;
    private String mimeType;

}
