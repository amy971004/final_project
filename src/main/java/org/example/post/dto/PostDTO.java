package org.example.post.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class PostDTO {
    private int contentNo;
    private String user_nickName;
    private String postContent;
    private Date uploadDate;
}
