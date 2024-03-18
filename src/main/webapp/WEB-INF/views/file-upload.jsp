<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>파일 업로드</title>
</head>
<body>

<h2>파일 업로드</h2>
<form action="upload" method="post" enctype="multipart/form-data">
    파일 선택: <input type="file" name="file" /><br/><br/>
    <button type="submit">업로드</button>
</form>

</body>
</html>
