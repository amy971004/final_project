<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>사진 및 글 내용 업로드</title>
</head>
<body>

<h2>사진 및 글 내용 업로드</h2>
<form action="upload" method="post" enctype="multipart/form-data">
    글 내용: <textarea name="content" rows="4" cols="50"></textarea><br/><br/>
    사진 선택: <input type="file" name="file" /><br/><br/>
    <button type="submit">업로드</button>
</form>

</body>
</html>
