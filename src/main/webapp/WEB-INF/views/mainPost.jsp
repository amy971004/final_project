<%@ page import="org.example.post.dto.PostDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp"%>

<!-- 슬라이더 -->
<link href="../../resources/css/bxslider.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<%--<script src="http://code.jquery.com/jquery-latest.min.js"></script>--%>

<script src='../../resources/js/post.js'></script>
<link href="../../resources/css/post.css" rel="stylesheet">
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
      integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
<script>
    function openModal() {
        const modal = document.querySelector('.modal');
        modal.style.display= 'block';
    }
</script>

<body>
<div class="modal">
    <div class="close_modal">X</div>
    <div class="modal_content">
        <div class="modal_header">
            <div class="modal_profile"><img src="#" alt="프로필 이미지"></div>
            <div class="modal_nickname">${post.userNickname}</div>
        </div>
        <div class="modal_body">
            <h2>모달창 제목</h2>
            <p>모달창 내용 </p>
        </div>
    </div>
</div>
<main>
    <c:forEach var="post" items="${postMap.postList}">
    <article class="post">
        <div>
            <article class="box-feed">
                <div class="head-feed">
                    <div id="menu_box${post.postId}" class="wrap-menu">
                        <div class="modify-menu">
                            <div class="modify-img"><i class="fa-regular fa-pen-to-square"></i></div>
                            <p class="modify-word">수정</p>
                        </div>
                        <div class="delete-menu" onclick="deletePost('/main/post/deletePost.do',${post.postId})">
                            <div class="delete-img"><i class="fa-regular fa-trash-can"></i></div>
                            <p class="delete-word">삭제</p>
                        </div>
                    </div>
                    <div class="profile-feed">
                        <img
                                class="img-profile-32px"
                                src="/main/post/profileImageDownload.do?userNickname=${post.userNickname}"
                                alt="프로필 이미지"
                        />
                        <div>
                            <p class="userName-feed">${post.userNickname}</p>
                            <p class="location-feed">${post.uploadDate}</p>
                        </div>
                    </div>
                    <span id="more${post.postId}" class="icon-more" onclick="show_menu(${post.postId},0)">
                        <i class="fa-solid fa-ellipsis"></i>
                    </span>
                </div>
                <div>
                    <div class="slider">
                        <c:forEach var="image" items="${postMap.imageList}">
                            <c:if test="${post.postId == image.postId}">
                                <div class="img">
                                    <img class="img-feed" src="/main/post/imageDownload.do?imageFileName=${image.fileName}&postId=${post.postId}" alt="피드 이미지" />
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <c:forEach var="likeBook" items="${postMap.likeBookList}">
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
                                <span class="icon" onclick="show_comment_box(${post.postId},0,this)"><i class="fa-regular fa-comment"></i></span>
                                <span class="icon"><i class="fa-regular fa-share-from-square"></i></span>
                            </div>
                            <c:choose>
                                <c:when test="${likeBook.bookmarkCheck == 0}">
                                    <span class="icon" onclick="bookClick(${likeBook.bookmarkCheck},${post.postId},'${loginNickname}', this)"><i class="fa-regular fa-bookmark"></i></span>
                                </c:when>
                                <c:when test="${likeBook.bookmarkCheck == 1}">
                                    <span class="icon-book-pushed" onclick="bookClick(${likeBook.bookmarkCheck},${post.postId},'${loginNickname}', this)"><i class="fa-solid fa-bookmark"></i></span>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="content">${post.content}</div>
                        <div id="tags">
                            <c:forEach var="tags" items="${postMap.tagsList}">
                                <c:if test="${tags.postId == post.postId}">
                                    <c:forEach var="tag" items="${tags.hashTag}">
                                        #${tag}
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </div>
                        <p id="like-cnt${post.postId}" class="text-like">좋아요 ${likeBook.likeCnt}개</p>
                        <p class="show_all_comment" onclick="show_all_comment(${post.postId})">전체 댓글 보기..</p>
                    </c:if>
                </c:forEach>
                <div class="comment_box" id="comment_box${post.postId}">
                    <c:forEach var="comment" items="${postMap.commentList}">
                        <c:if test="${comment.postId == post.postId}">
                            <div>
                                <div class="comment">
                                    <b class="writer">${comment.user_Nickname}&nbsp;</b>
                                    <span class="post-comment">
                                            ${comment.postComment}
                                    </span>
                                </div>
                            </div>
                            <div class="reply_comment">
                                <span>답글달기..</span>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                    <%--                <div class="box-comment"></div>--%>

                <div class="box-chat">
                    <span class="icon-smile"><i class="fa-regular fa-face-smile"></i></span>
                        <%--<img class="img-icon" src="src/icon/smile.png" alt="이모지 아이콘" />--%>
                    <div class="addComment">
                        <input  id="inputComment${post.postId}"
                                class="input-chat"
                                type="text"
                                placeholder="댓글 달기..."
                        />
                        <button class="btn-chat" onclick="addComment(${post.postId},'${loginNickname}')">게시</button>
                    </div>
                </div>
            </article>
        </div>
    </article>
    </c:forEach>
</main>
</body>
</html>
