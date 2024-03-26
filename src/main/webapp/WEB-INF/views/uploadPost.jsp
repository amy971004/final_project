<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>사진 및 글 내용 업로드</title>
    <link rel="stylesheet" href="../../resources/css/uploadPost.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    /* 이미지 보여주기  + 파일 첨부 갯수 제한 */
    function readURL(input){
        let preview_box = $('#preview_box');
        // 파일 첨부 갯수 제한
        if(input.files.length > 4){
            alert('이미지는 최대 4개 까지 가능합니다.');
            $('.files').val('');
            preview_box.empty();
        }
        // 이미지 미리보기
        if(input.files && input.files.length > 0){
            preview_box.empty();
            for(let i=0; i < input.files.length; i++){
                let reader = new FileReader();
                reader.onload = function (e){
                    let lastModified = input.files[i].lastModified;
                    $('#preview_box').append('<div class="image_div" id="'+lastModified+'"><img id="preview_image" src="'+e.target.result+'"/><p id="txt" class="'+lastModified+'">삭제</p></div>');
                }
                reader.readAsDataURL(input.files[i]);
            }
        }
    }
    // 사진 클릭 시 해당 사진 삭제
    $(document).on('click','p', function (e){
        let input = $('.files')[0].files;

        let dataTranster = new DataTransfer();
        let target = e.target;
        let removeId = $(target).attr("class");

        for(let i=0; i < input.length; i++){
            let file = input[i];
            if(file.lastModified != removeId){
                dataTranster.items.add(file);
            }
        }
        $('.files')[0].files = dataTranster.files;
        $('#'+removeId).remove();

    })

    /* 업로드 확인 여부 */
    function upload_check(){
        // 이미지 파일 첨부 확인 여부
        let files = $('.files')[0].files;
        if(files.length === 0){
            alert('이미지 1개 이상 업로드 하세요.');
        }
        else{
            // 업로드 누를 시
            if(confirm('업로드 하시겠습니까?') === true){ // 확인 누를 시
                let frm = document.uploadForm;
                frm.submit();
            }
        }
    }
    /* 뒤로 가기 */
    function page_back() {
        // 이전 페이지로 이동
        history.go(-1);
    }
    /* 취소 확인 여부 */
    function cancel(){
        // 확인 클릭 시 메인 페이지로 이동
        if(confirm('취소하시겠습니까?') === true){
            location.href = "/main";
        }
        // 취소 클릭 시 계속 작성
    }
</script>
<body>
<div id="upload_from_box">
    <h2>게시물 업로드</h2>
    <form name="uploadForm" id="uploadFrm" action="upload.do" method="post" enctype="multipart/form-data">
        <div id="content_box">
            <textarea id="content_input" name="content" rows="10" cols="60" maxlength="999"></textarea>
        </div>
        <div id="image_upload_box">
            <label id="image_upload_btn" for="file"><i class="fa-solid fa-images"></i>사진 업로드</label>
            <input type="file" class="files" id="file" name="file" onchange="readURL(this)" hidden multiple>
        </div>
        <div id="preview_box"></div>
        <button id="upload-btn" type="button" onclick="upload_check()"><i class="fa-solid fa-arrow-up-from-bracket"></i>업로드</button>
        <button id="cancel_btn" type="button" onclick="cancel()"><i class="fa-solid fa-xmark"></i>취소</button>
    </form>
</div>
</body>
</html>
