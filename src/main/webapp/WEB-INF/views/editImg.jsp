<%--
  Created by IntelliJ IDEA.
  User: andy0
  Date: 2024-03-20
  Time: 오전 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>프로필 이미지 및 소개 수정</title>
</head>
<body>
    <h2>Edit Image</h2>
    <form action="upload.do" method="post" enctype="multipart/form-data">
        글 내용: <textarea name="content" rows="4" cols="50"></textarea><br/><br/>
        사진 선택: <input type="file" name="file" /><br/><br/>
        <button type="submit">업로드</button>
    </form>
</body>
</html>
