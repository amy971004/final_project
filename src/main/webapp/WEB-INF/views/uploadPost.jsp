<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" href="../../resources/css/uploadPost.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>새 게시물 업로드</title>
</head>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="../../resources/js/uploadPost.js"></script>
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
                    <div class="image_upload_box">
                        <label id="image_upload_btn" for="file"><i class="fa-solid fa-images"></i>사진 업로드</label>
                        <input type="file" class="files" id="file" name="file" onchange="readURL(this)" hidden multiple>
                    </div>
                    <div class="text1_box">
                        <span class="text1">* 해당 이미지 클릭 시 삭제 *</span>
                    </div>
                </td>
                <td>
                    <div class="userProfile">
                        <c:choose>
                            <c:when test="${profile.profileImg == null}">
                                <img src="../../resources/img/profile/defaultProfile.png" alt="기본 프로필 사진">
                            </c:when>
                            <c:otherwise>
                                <img src="${contextPath}/main/profile/download.do?imageFileName=${profile.profileImg}&accountId=${profile.accountId}" alt="">
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
                    <div class="tag_label">
                        <span class="tag_text">해시태그</span>
                    </div>
                    <div class="input_tag_box">
                        <input type="text" id="tag" name="tags" spellcheck="false" placeholder="엔터키로 입력" onkeydown="if(event.keyCode=='13'){event.preventDefault(); hashTag();}">
                        <input type="text" name="hashTags" id="hashTagg" hidden>
                    </div>
                    <div class="hashtag_box"></div>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>