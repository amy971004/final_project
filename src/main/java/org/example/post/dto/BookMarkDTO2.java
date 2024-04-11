package org.example.post.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class BookMarkDTO2 {
    private int bookMarkId;
    private int postId;
    private Date writeDate;
    private String userNickname;
    private String fileName;
    private int commentCnt;
    private int likeCnt;
    private String date;

}
