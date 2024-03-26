<%@ page import="org.example.post.dto.PostDTO" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-03-18
  Time: 오후 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="../../resources/css/post.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
function likeClick(like,postId,loginNickname, target) {
/*    const params = {
        like: like
        , postId: postId
        , loginNickname: loginNickname
    };*/

    $.ajax({
        type:"get",
        url:"/post/heartPush.do",
        data: {
            like : like,
            postId : postId,
            loginNickname : loginNickname
        },
        contentType : "application/json",
        success:function(res){
            if(like == 1){
                const icon_like = document.getElementById("icon-like-pushed");
                target.innerHTML = '<i class="fa-regular fa-heart"></i>';
                target.style.margin = '0 15px 0 0';
                target.style.fontSize = '1.5rem';
                target.style.color = 'black';
                target.setAttribute("id", "icon-like");
                target.setAttribute('onclick','likeClick(0,'+postId+',"'+loginNickname+'", this)');

            }else if(like == 0) {
                const icon_like = document.getElementById("icon-like");
                target.innerHTML = '<i class="fa-solid fa-heart"></i>';
                target.style.color = 'red';
                target.style.margin = '0 15px 0 0';
                target.style.fontSize = '1.5rem';
                target.setAttribute("id", "icon-like-pushed");
                target.setAttribute('onclick','likeClick(1,'+postId+',"'+loginNickname+'", this)');

            }
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("통신 실패")
        }
    });
}
</script>
<body>
<%--    게시물<br>
    <c:forEach var="post" items="${postMap.postList}" >
        게시물 번호: '${post.contentNo}'
        게시날짜 : '${post.uploadDate}'
        유저닉네임 : '${post.userNickname}'
        게시글 : '${post.postContent}'
        <c:forEach var="likeBook" items="${postMap.likeBookList}">
            <c:if test="${post.contentNo == likeBook.contentNo}">
                좋아요 : '${likeBook.likeCheck}'
                북마크 : '${likeBook.bookmarkCheck}'
                좋아요 갯수 : ${likeBook.likeCnt}
            </c:if>
        </c:forEach>
        <img src="imageDownload.do?contentNo=${post.contentNo}" alt="게시물"><br>
        <c:forEach var="tags" items="${postMap.tagsList}">
            <c:if test="${tags.contentNo == post.contentNo}">
                <c:choose>
                   <c:when test="${tags.hashTag.size() == 0}">
                    <br>
                   </c:when>
                <c:otherwise>
                    태그 :
                    <c:forEach var="tag" items="${tags.hashTag}">
                         ${tag}
                    </c:forEach>
                    <br>
                </c:otherwise>
                </c:choose>
                <c:forEach var="comment" items="${postMap.commentList}">
                    <c:if test="${comment.contentNo == post.contentNo}">
                      ${comment.user_Nickname} : ${comment.commentText} <br>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:forEach>
        <br><br>
    </c:forEach>--%>
<%--
로그인된 닉네임
${LoginId}
--%>
<c:forEach var="post" items="${postMap.postList}">
<main>
    <div>
        <article class="box-feed">
            <div class="head-feed">
                <div class="profile-feed">
                    <img
                            class="img-profile-32px"
                            src="../../resources/img/profile.jpg"
                            alt="프로필 이미지"
                    />
                    <div>
                        <p class="userName-feed">${post.userNickname}</p>
                        <p class="location-feed">${post.uploadDate}</p>
                    </div>
                </div>
                <span class="icon-more"><i class="fa-solid fa-ellipsis"></i></span>
            </div>

            <img class="img-feed" src="../../resources/img/cat.jpg" alt="피드 이미지" />

            <c:forEach var="likeBook" items="${postMap.likeBookList}">
                <c:if test="${post.postId == likeBook.contentNo}">
                        <div class="icon-feed">
                            <div>
                                <c:choose>
                                    <c:when test="${likeBook.likeCheck == 1}">
                                        <span id="icon-like-pushed" class="icon-like-pushed"  onclick="likeClick(${likeBook.likeCheck},${post.postId},'${LoginId}', this)">
                                            <i class="fa-solid fa-heart"></i>
                                        </span>
                                    </c:when>
                                    <c:when test="${likeBook.likeCheck == 0}">
                                        <span id="icon-like" class="icon" onclick="likeClick(${likeBook.likeCheck},${post.postId},'${LoginId}', this)">
                                            <i class="fa-regular fa-heart"></i>
                                        </span>
                                    </c:when>
                                </c:choose>
                                <span class="icon"><i class="fa-regular fa-comment"></i></span>
                                <span class="icon"><i class="fa-regular fa-share-from-square"></i></span>
                            </div>
                            <c:choose>
                                <c:when test="${likeBook.bookmarkCheck == 0}">
                                    <span class="icon"><i class="fa-regular fa-bookmark"></i></span>
                                </c:when>
                                <c:when test="${likeBook.bookmarkCheck == 1}">
                                    <span class="icon-book-pushed"><i class="fa-solid fa-bookmark"></i></span>
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
                    <p class="text-like">좋아요 ${likeBook.likeCnt}개</p>
                </c:if>
            </c:forEach>
           <c:forEach var="comment" items="${postMap.commentList}">
               <c:if test="${comment.postId == post.postId}">
                    <div class="comment">
                        <b class="writer">${comment.user_Nickname}&nbsp;</b>
                        <span class="post-comment">
                            ${comment.postComment}
                        </span>
                    </div>
               </c:if>
           </c:forEach>
            <div class="box-comment"></div>

            <div class="box-chat">
                <span class="icon-smile"><i class="fa-regular fa-face-smile"></i></span>
                <%--<img class="img-icon" src="src/icon/smile.png" alt="이모지 아이콘" />--%>
                <form>
                    <input
                            class="input-chat"
                            type="text"
                            placeholder="댓글 달기..."
                    />
                    <button class="btn-chat" type="submit">게시</button>
                </form>
            </div>
        </article>
    </div>
</main>
</c:forEach>
</body>
</html>
