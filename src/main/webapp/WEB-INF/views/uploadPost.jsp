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
    /* 이미지 보여주기 */
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

/*    function num(){
        let file = document.querySelectorAll(".files");
        console.log(file);
        console.log(file[0].outerHTML)

       let files = $('.files');
        console.log("input=file 수 : " + files.length);
        for(let i=0; i < files.length; i++){
            console.log("1 -> 파일 O / 0 -> 파일 X : " + files[i].files.length);
            for(let i=0; i < files.length; i++){
                // 빈 파일 경우 삭제
                if(files[i].files.length === 0){
                    $('#file'+i).remove();
                }
            }
        }
    }*/

    /* 이미지 업로드 */
    let cnt = 0;
    function fn_addFile(){
        // 이미지 개수 제한
        // input file 개수
        let files = $('.files');
        if(files.length === 4){
            alert('이미지는 최대 4개 까지 가능합니다.');
        }else{
            $('#d_file').append("<input hidden class='files' type='file' name='file' id='file"+cnt+"' onchange='readURL(this)'>")
            $('#file'+cnt).click();
            cnt++;
        }
    }

    /* 업로드 확인 여부 */
    function upload_check(){
        // 이미지 파일 첨부 확인 여부
        let files =  $('.files');
        if(files.length === 0){
            alert('이미지 1개 이상 업로드 하세요.');
        }
        else{
            // 업로드 누를 시
            if(confirm('업로드 하시겠습니까?') === true){ // 확인 누를 시
                let frm = document.uploadForm;
                frm.submit();
            } else{ // 취소 누를 시
                history.go(-1); // 이전 페이지로 이동
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
            <div id="preview_box"></div>
            <div id="d_file"></div>
            <div id="image_upload_box">
                <button id="image_upload_btn" type="button" onclick="fn_addFile()"><i class="fa-solid fa-images"></i>사진 업로드</button>
            </div>
            <div id="back_page_box">
                <button id="back_page" type="button" onclick="page_back()"><i class="fa-solid fa-arrow-right-from-bracket"></i>뒤로가기</button>
            </div>
            <button id="upload-btn" type="button" onclick="upload_check()"><i class="fa-solid fa-arrow-up-from-bracket"></i>업로드</button>
            <button id="cancel_btn" type="button" onclick="cancel()"><i class="fa-solid fa-xmark"></i>취소</button>
        </form>
    </div>
</body>
</html>
