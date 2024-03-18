package org.example.post.dto;

import lombok.Data;

@Data
public class ImageDTO {
    // 이미지 고유 번호
    private int imageNo;
    // 이미지가 속한 콘텐츠의 고유 번호
    private int contentNo;
    // 이미지 파일의 이름
    private String fileName;
    // 이미지 파일에 접근할 수 있는 URL 주소
    private String fileUrl;
}
