<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head>
    <meta charset="UTF-8">
    <link href="../../resources/css/profile.css" rel="stylesheet" type="text/css" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ include file="header.jsp"%>
    <link href="../../resources/css/main.css" rel="stylesheet" type="text/css" />
    <link href="../../resources/css/bxslider1.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="../../resources/css/reset.css">
    <link rel="stylesheet" href="../../resources/css/common.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/nav.css">
    <c:set var="contextPath" value="${pageContext.request.contextPath }" />
    <title>프로필</title>
</head>
<body>
<!--프로필 섹션-->
<header>

    <div class="container">

        <div class="profile">

            <div class="profile-image">
                <c:choose>
                    <c:when test="${profile.profileImg == null}">
                        <img src="/resources/img/profile/defaultProfile.png" alt=""/>
                    </c:when>
                    <c:otherwise>
                        <img src="${contextPath}/main/profile/download.do?imageFileName=${profile.profileImg}&accountId=${profile.accountId}"  alt=""/>
                    </c:otherwise>
                </c:choose>

            </div>

            <div class="profile-user-settings">

                <h1 class="profile-user-name">${profile.userId} / ${profile.userNickname}</h1>

                <button class="btn profile-edit-btn" onclick="location.href='http://localhost:8081/main/profile/modprofile.do?id=${profile.userId}'">Edit Profile</button>
                <button class="btn profile-settings-btn" onclick="location.href='http://localhost:8081/main/profile/editImg.do'">Edit Image</button>

            </div>

            <div class="profile-stats">
                <c:set var="postCount" value="0" />
                <c:forEach var="post" items="${profileMap.postDTO}">
                    <c:set var="postCount" value="${postCount + 1}" />
                </c:forEach>
                <c:set var="followerCount" value="0" />
                <c:forEach var="follow" items="${profileMap.followDTO}">
                    <c:set var="followerCount" value="${followerCount + 1}"/>
                </c:forEach>
                <c:set var="followingCount" value="0" />
                <c:forEach var="following" items="${profileMap.followingDTO}">
                    <c:set var="followeringCount" value="${followingCount + 1}"/>
                </c:forEach>


                <ul class="profile-count">
                    <li><span class="profile-stat-count">${postCount}</span> posts</li>
                    <li class="follower" onclick='show_follower_modal("${profile.userNickname}")'><span  class="profile-stat-count">${followerCount}</span> followers</li>
                    <li class="following" onclick='show_following_modal("${profile.userNickname}")'><span class="profile-stat-count">${followingCount}</span> following</li>
                </ul>

            </div>

            <div class="profile-bio">

                <p><span class="profile-real-name">${profile.userName}</span><br>${profile.introduction}</p>

            </div>

        </div>
        <!-- End of profile section -->

    </div>
    <!-- End of container -->

</header>

<hr style=" width: 1100px; margin-left: calc(50% + 25px); transform: translateX(-50%)">

<main style="margin-top: 10px">



    <article class="container">
        <div class="gallery">
            <c:forEach var="post" items="${profileMap.postDTO}">
                <div class="gallery-item" tabindex="0" data-postId="${post.postId}">
                    <div class="head-feed">
                        <!-- 수정 삭제 메뉴 -->
                        <div id="menu_box${post.postId}" class="wrap-menu" style="z-index: 100">
                            <div class="modify-menu">
                                <div class="modify-img"><i class="fa-regular fa-pen-to-square"></i></div>
                                <p class="modify-word">수정</p>
                            </div>
                            <div class="delete-menu" onclick="deletePost('/main/profile/deletePost.do',${post.postId})">
                                <div class="delete-img"><i class="fa-regular fa-trash-can"></i></div>
                                <p class="delete-word">삭제</p>
                            </div>
                        </div>
                        <!-- 수정 삭제 메뉴 -->
                        <div class="profile-feed">
                            <div>
                                <p class="location-feed">${post.uploadDate}</p>
                            </div>
                        </div>
                        <span id="more${post.postId}" class="icon-more" onclick="show_menu(${post.postId},0)">
                        <i class="fa-solid fa-ellipsis"></i>
                        </span>
                    </div>
                    <div class="slider">
                        <c:forEach var="image" items="${profileMap.imageDTO}">
                            <c:if test="${post.postId eq image.postId}">
                                <img src="${contextPath}/main/post/imageDownload.do?imageFileName=${image.fileName}&postId=${post.postId}" class="gallery-image" alt="">
                            </c:if>
                        </c:forEach>
                    </div>
                    <c:forEach var="likeBook" items="${profileMap.likeBookList}">
                        <c:if test="${post.postId == likeBook.contentNo}">
                            <div class="icon-feed">
                                <div>
                                    <c:choose>
                                        <c:when test="${likeBook.likeCheck == 1}">
                                        <span id="icon-like-pushed" class="icon-like-pushed"  onclick="likeClick(${likeBook.likeCheck},${post.postId},'${loginNickname}', this,'${likeBook.likeCnt}')">
                                            <i class="fa-solid fa-heart"></i>
                                        </span>
                                        </c:when>
                                        <c:when test="${likeBook.likeCheck == 0}">
                                        <span id="icon-like" class="icon" onclick="likeClick(${likeBook.likeCheck},${post.postId},'${loginNickname}', this,'${likeBook.likeCnt}')">
                                            <i class="fa-regular fa-heart"></i>
                                        </span>
                                        </c:when>
                                    </c:choose>
                                    <span class="icon" onclick="show_all_comment(${post.postId},'${profile.userNickname}')"><i class="fa-regular fa-comment"></i></span>
                                    <span class="icon"><i class="fa-regular fa-share-from-square"></i></span>
                                </div>
                                <c:choose>
                                    <c:when test="${likeBook.bookmarkCheck == 0}">
                                        <span class="icon" onclick="bookClick(${likeBook.bookmarkCheck},${post.postId},'${profile.userNickname}', this)"><i class="fa-regular fa-bookmark"></i></span>
                                    </c:when>
                                    <c:when test="${likeBook.bookmarkCheck == 1}">
                                        <span class="icon-book-pushed" onclick="bookClick(${likeBook.bookmarkCheck},${post.postId},'${profile.userNickname}', this)"><i class="fa-solid fa-bookmark"></i></span>
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="content">${post.content}</div>
                            <div id="tags">
                                <c:forEach var="tags" items="${profileMap.tagsList}">
                                    <c:if test="${tags.postId == post.postId}">
                                        <c:forEach var="tag" items="${tags.hashTag}">
                                            #${tag}
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <p id="like-cnt${post.postId}" class="text-like" onclick="show_like_modal(${post.postId},'${profile.userNickname}')">좋아요 ${likeBook.likeCnt}개</p>
                            <p class="show_all_comment" id="${post.postId}" onclick="show_all_comment(${post.postId},'${profile.userNickname}')">전체 댓글 보기..</p>
                        </c:if>
                    </c:forEach>
                </div>
            </c:forEach>
            <!-- End of gallery -->
        </div>
        <!-- End of container -->
    </article>


</main>
<!--좋아요 모달창 -->
<c:forEach var="post" items="${profileMap.postDTO}">
    <!--좋아요 모달창 -->
    <div class="like_modal" id="like_modal${post.postId}">
        <div class="like_modal_content">
            <div class="like_modal_header">
                <div class="like_header_subject">좋아요</div>
                <div class="like_header_closebtn" onclick="close_like_modal(${post.postId})"><span>X</span></div>
            </div>
            <div id="like_modal_body${post.postId}" class="like_modal_body">
            </div>
        </div>
    </div>
    <!-- 좋아요 모달창 --->
    <!-- 댓글 모달창 -->
    <div class="modal" id="modal${post.postId}">
        <div class="close_modal" onclick="close_modal('${post.postId}')">X</div>
        <div class="modal_content">
            <div class="modal_header">
                <div class="modal_header_feed">
                    <div class="modal_profile">
                        <a href="${contextPath}/main/profile/userProfile.do?userNickname=${post.userNickname}">
                            <img
                                    class="modal_profile_32px"
                                    src="/main/post/profileImageDownload.do?userNickname=${post.userNickname}" alt="모달창 상단 작성자 프로필 이미지">
                        </a>
                    </div>
                    <div class="modal_nickname">${post.userNickname}</div>
                </div>
                <div class="modal_menu_icon"><i class="fa-solid fa-ellipsis"></i></div>
            </div>
            <div class="modal_body" id="modal_body${post.postId}">
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
    <%-- 팔로우 모달창 --%>
    <div class="follow_modal">
        <div class="follow_modal_content">
            <div class="follow_modal_header">
                <div class="follow_header_subject">팔로우</div>
                <div class="follow_header_closebtn" onclick="close_follow_modal()"><span>닫기</span></div>
            </div>
            <div id="follow_modal_body" class="follow_modal_body">
            </div>
        </div>
    </div>
</c:forEach>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="../../resources/js/logout.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src='../../resources/js/profile.js'></script>
</body>
</html>
