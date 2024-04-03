<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="../../resources/css/uploadPost.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="../../resources/css/nav.css">
    <link rel="stylesheet" href="../../resources/css/reset.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>새 게시물 업로드</title>
</head>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>
    // 이미지 미리보기 + 파일들 삭제
    function allDelete(){
        $('#content_input').val('');
        $('.files').val('');
        $('.slider').slick('slickRemove', null, null, true);
        $('.slider').slick('refresh');
    }

    /* 이미지 보여주기  + 파일 첨부 갯수 제한 */
    function readURL(input){
        // 파일 첨부 갯수 제한
        if(input.files.length > 10){
            alert('이미지는 최대 10개 까지 가능합니다.');
            allDelete();
        }
        if(input.files.length === 0){
            $('.slider').slick('slickRemove', null, null, true);
            $('.slider').slick('refresh');
        }

        if(input.files && input.files.length > 0){

            $('.slider').slick('slickRemove', null, null, true);
            $('.slider').slick('refresh');
            for(let i=0; i < input.files.length; i++) {

                if(!input.files[i].type.includes('image/')){
                    alert('이미지만 가능합니다.');
                    allDelete();
                }
                // 파일 용량 제한
                let imageSize = input.files[i].size;
                let minSize = 5 * 1024;
                let maxSize = 10 * 1024 * 1024;
                if(imageSize < minSize){
                    alert('파일의 크기 5KB 이상이어야 합니다.');
                    allDelete();
                }
                console.log(imageSize);
                if (imageSize > maxSize) {
                    alert('개별첨부 가능한 파일은 10MB 이하여야 합니다.');
                    allDelete();
                }else{
                    // 이미지 미리보기
                    let reader = new FileReader();
                    reader.onload = function (e) {
                        let lastModified = input.files[i].lastModified;
                        $('.slider').slick('slickAdd','<div class="image_div" id="' + lastModified + '"><img id="' + lastModified + '" src="' + e.target.result + '" alt=""/></div></div>');
                    }
                    reader.readAsDataURL(input.files[i]);
                }
            }
        }
    }
    // 사진 클릭 시 해당 사진 삭제
    $(document).on('click','img', function (e){
        let input = $('.files')[0].files;
        let dataTranster = new DataTransfer();
        let target = e.target;
        let removeSlide = $(target).parent().attr("data-slick-index");
        let removeId = $(target).attr("id");

        for(let i=0; i < input.length; i++){
            let file = input[i];
            if(file.lastModified != removeId){
                dataTranster.items.add(file);
            }
        }
        $('.files')[0].files = dataTranster.files;
        $('.slider').slick('slickRemove', removeSlide);
        $('.slider').slick('refresh');

    })
    /* 업로드 확인 여부 */
    function upload_check(){
        let files = $('.files')[0].files;
        let totalSize = 50 * 1024 * 1024;
        let totalImageSize = 0;
        for(let i=0; i<files.length; i++){
            totalImageSize += files[i].size;
        }
        // 이미지 파일 첨부 확인 여부
        if(files.length === 0){
            alert('이미지 1개 이상 업로드 하세요.');
        }
        // 모든 첨부 파일 용량 제한
        else if(totalImageSize > totalSize){
            alert('모든 첨부파일 용량이 50MB 이하여야 합니다.');
        }else{
            // 업로드 누를 시
            if(confirm('업로드 하시겠습니까?') === true){ // 확인 누를 시
                let frm = document.uploadForm;
                frm.submit();
            }
        }
    }
    // 글자 수 체크
    function count(){
        let content = $('#content_input').val();
        if(content.length <= 1500){
            $('.text_count').text(content.length + '/1500');
        }else{
            $('.text_count').value = content.substring(0,1500);
        }
    }

    /* 취소 확인 여부 */
    function cancel(){
        // 확인 클릭 시 메인 페이지로 이동
        if(confirm('취소하시겠습니까?') === true){
            location.href = "/main";
        }// 취소 클릭 시 계속 작성
    }
    /* 이미지 미리보기 슬라이드 */
    $(document).ready(function (){
        $( '.slider' ).slick( {
            slidesToShow: 1,
            slidesToScroll: 1,
            dots:true,
            speed: 700,
            centerMode:true,
            centerPadding: '0px',
            infinite: false,
        } );
    });
</script>
<style>
    .slick-prev:before, .slick-next:before {
        color: #444444;
        font-size: 40px;
    }
    .slick-next{
        right: 20px;
    }
    .slick-prev{
        z-index: 1;
        left: 2px;
    }
</style>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<body>
<div id="upload_from_box">
    <form name="uploadForm" id="uploadFrm" action="upload.do" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td colspan="2">
                    <div class="image_upload_box">
                        <button id="cancel_btn" type="button" onclick="cancel()"><i class="fa-solid fa-xmark"></i>취소</button>
                        <span>새 게시물 업로드</span>
                        <button id="upload-btn" type="button" onclick="upload_check()">업로드<i class="fa-solid fa-arrow-up-from-bracket"></i></button>
                        <hr>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="image_preview">
                        <div class="slider"></div>
                    </div>
                </td>
                <td>
                    <div class="userProfile">
                        <c:choose>
                            <c:when test="${profile.profileImg == null}">
                                <img src="../../resources/img/profile/defaultProfile.png" alt="기본 프로필 사진">
                            </c:when>
                            <c:otherwise>
                                <img src="${contextPath}/main/profile/download.do?imageFileName=${profile.profileImg}&userId=${profile.userId}" alt="">
                            </c:otherwise>
                        </c:choose>
                        <span class="userNickname">${profile.userNickname}</span>
                    </div>
                    <div class="content_box">
                        <textarea id="content_input" name="content" rows="15" cols="40" maxlength="1500" spellcheck="false" onkeyup="count()" placeholder="내용을 입력하세요"></textarea>
                    </div>
                    <div class="text_count_box">
                        <span class="text_count">0/1500</span>
                        <hr>
                    </div>
                    <div class="image_upload_box">
                        <label id="image_upload_btn" for="file"><i class="fa-solid fa-images"></i>사진 업로드</label>
                        <input type="file" class="files" id="file" name="file" onchange="readURL(this)" hidden multiple>
                    </div>
                    <div class="text1_box">
                        <span class="text1">* 해당 이미지 클릭 시 삭제 *</span>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<script src="../../resources/js/logout.js"></script>
</body>
</html>