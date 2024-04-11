<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!--슬라이더-->
<link rel="stylesheet" href="../../resources/css/post.css">
<link href="../../resources/css/bxslider2.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
    .bx-wrapper img {
        margin: 0 auto;
    }
</style>
<script>
    $(document).ready(function(){
        $(".slider2").bxSlider({
            mode: 'fade',
            captions: true,
            slideWidth: 500
        });
    });
</script>
<body>
<main>
<h1>저장한 게시물</h1>
<div id="wrapper">
    <c:forEach var="post" items="${bookMarkDTOList}">
        <div class="detail_modal" id="modal${post.postId}">
            <div class="detail_menu_modal" id="detail_menuBtn${post.postId}">
                <div id="detail_menu${post.postId}" class="detail_menu">
                    <div class="bookMarkDel-menu" onclick="bookMarkCancle(${post.bookMarkId})">북마크 취소</div>
                    <div class="cancle-menu" onclick="show_detail_menu(${post.postId},1)">취소</div>
                </div>
            </div>
            <div class="close_detail_modal" onclick="close_modal(${post.postId},1)">X</div>
            <div class="bookMarkDetailwrapper">
                <div class="detail_image_box">
                    <div class="detail_image">
                        <div class="slider2">
                            <c:forEach var="image" items="${postImageList}">
                                <c:if test="${post.postId == image.postId}">
                                   <div class="img">
                                       <img class="img-feed2" src="/main/post/imageDownload.do?imageFileName=${image.fileName}&postId=${post.postId}" alt="상세이미지" style="width: 500px">
                                  </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="detail_comment_box">
                    <div class="detail_modal_header">
                        <div class="modal_header_feed">
                            <div class="modal_profile">
                                <a href="${contextPath}/main/profile/userProfile.do?userNickname=${post.userNickname}">
                                    <img
                                            class="modal_profile_32px"
                                            src="/main/post/profileImageDownload.do?userNickname=${post.userNickname}" alt="모달창 상단 작성자 프로필 이미지">
                                </a>
                            </div>
                            <%--<div class="modal_nickname">${post.userNickname}</div>--%>
                            <div>
                                <p class="userName-feed">${post.userNickname}</p>
                                <p class="location-feed">${post.date}</p>
                            </div>
                        </div>
                        <div class="modal_menu_icon" onclick="show_detail_menu(${post.postId},0)"><i class="fa-solid fa-ellipsis"></i></div>
                    </div>
                    <div class="modal_body" id="modal_body${post.postId}" style="height: 570px">
                    </div>
                    <div class="modal_box_chat">
                        <span class="icon-smile"><i class="fa-regular fa-face-smile"></i></span>
                        <div class="addReplyComment">
                            <input  id="modal_inputComment${post.postId}"
                                    class="modal_input-chat"
                                    type="text"
                                    value=""
                                    placeholder="댓글 달기..."
                            />
                            <button id="btn-chat${post.postId}" class="btn-chat" onclick="add_modal_Comment(${post.postId},'${loginNickname}')">게시</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="image-container" id="post${post.postId}" onclick="show_all_comment(${post.postId},'${loginNickname}')">
            <img src="/main/post/imageDownload.do?imageFileName=${post.fileName}&postId=${post.postId}" alt="게시물 이미지"/>
            <div class="image-caption">
                <h1><i class="fa-solid fa-heart"></i><span>${post.likeCnt}</span><i class="fa-solid fa-comment"></i><span>${post.commentCnt}</span></h1>
                <p></p>
            </div>
        </div>
    </c:forEach>
</div>
</main>
<%--<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="../../resources/js/logout.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>--%>
<script src='../../resources/js/post.js'></script>

</body>

