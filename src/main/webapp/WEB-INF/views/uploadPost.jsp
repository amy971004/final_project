<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>사진 및 글 내용 업로드</title>
    <link rel="stylesheet" href="../../resources/css/uploadPost.css" />
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function readURL(input){
        if(input.files && input.files[0]){
            let reader = new FileReader();
            reader.onload = function (e){
                let img =$('<img id="preview_image"/>');
                $(img).attr('src',e.target.result);
                $('#preview_box').append(img);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $(document).ready(function() {
        $('#file_input').change(function() {
            let fileNamesDiv = $('#file_names');
            fileNamesDiv.empty(); // 이전에 표시된 파일 이름을 지웁니다.

            let files = $(this).prop('files');
            if (files.length > 0) {
                for (let i = 0; i < files.length; i++) {
                    let fileName = files[i].name;
                    let fileNameNode = $('<p>').text(fileName);
                    fileNamesDiv.append(fileNameNode);
                }
            } else {
                fileNamesDiv.text('선택된 파일이 없습니다.');
            }
        });
    });
    function fn_addFile(){
        $('#d_file').append("<br><input type='file' name='file' onchange='readURL(this)'>");
    }
</script>
<body>
    <div id="upload_from_box">
        <h2>게시물 업로드</h2>
        <form id="uploadFrm" action="upload.do" method="post" enctype="multipart/form-data">
            글 내용
            <div id="content_box">
                <textarea id="content_input" name="content" rows="10" cols="50" maxlength="999" required></textarea>
            </div>

            <div id="preview_box"></div>
            <div id="d_file">
                <input type="file" name="file" onchange='readURL(this)'>
            </div>
            <div id="image_add_box">
                <button id="image_add_btn" type="button" onclick="fn_addFile()">사진추가</button>
            </div>
            <div id="upload_btn_box">
                <button id="upload-btn" type="submit">업로드</button>
            </div>
        </form>
    </div>
</body>
</html>
