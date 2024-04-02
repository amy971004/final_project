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
    // 좋아요 목록 보기
    function show_like_modal(postId, loginNickname) {
        const modal = document.querySelector("#like_modal"+postId);
        const body = document.getElementsByTagName('body');
        body[0].style.overflow = 'hidden';
        modal.style.display = 'flex';

        // 해당 게시물의 좋아요 정보 가져오기
        $.ajax({
            type:"get",
            url:"/main/post/getLikeInfo.do",
            contentType: "application/json",
            data: {
                postId : postId,
                loginNickname : loginNickname
            },
            success: function (data){
                const like_article = document.querySelector("#like_modal_body"+postId);
                let content = "";
                let arr = [];
                let like = data.likeInfo;
                let follow = data.followList;
                let cnt = data.followList.length;
                for(let i=0;i<cnt;i++){
                        if(like.includes(follow[i])){
                            let like_article_content = "<div class='like_article'>";
                            like_article_content += "<div class='like_profile'>";
                            like_article_content += "<img class='img-profile-32px' src='/main/post/profileImageDownload.do?userNickname="+follow[i]+"' alt='프로필이미지'>";
                            like_article_content += "<div class='like_nickName'>"+follow[i]+"</div></div>";
                            like_article_content += "<input class='following_btn' type='button' value='팔로잉' onclick='following(\"${loginNickname}\",\""+follow[i]+"\",this)'></div>";
                            content += like_article_content;
                            arr.push(follow[i]);

                        }
                }
                // 팔로우 목록에 있는 닉네임을 좋아요 목록에서 제외
                for(let j=0;j<arr.length;j++){
                    like.splice(like.indexOf(arr[j]),1);
                }
                for(let i=0;i<like.length;i++){
                    let like_article_content = "<div class='like_article'>";
                    like_article_content += "<div class='like_profile'>";
                    like_article_content += "<img class='img-profile-32px' src='/main/post/profileImageDownload.do?userNickname="+like[i]+"' alt='프로필이미지'>";
                    like_article_content += "<div class='like_nickName'>"+like[i]+"</div></div>";
                    like_article_content += "<input class='follow_btn' type='button' value='팔로우' onclick='follow(\"${loginNickname}\",\""+like[i]+"\",this)'></div>";
                    content += like_article_content;
                }

                like_article.innerHTML = content;
            },
            error:function (XMLHttpRequest, textStatus, errorThrown){
                console.log("가져오기 실패");
            }
        });
    }
</script>
<body>
<main>
    <c:forEach var="post" items="${postMap.postList}">
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
                            <img
                                    class="modal_profile_32px"
                                    src="/main/post/profileImageDownload.do?userNickname=${post.userNickname}">
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
        <!-- 댓글 모달창 -->
    <article class="post">
        <div>
            <article class="box-feed">
                <div class="head-feed">
                    <!-- 수정 삭제 메뉴 -->
                    <c:if test="${loginNickname == post.userNickname}">
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
                    </c:if>
                    <!-- 수정 삭제 메뉴 -->
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
                        <p id="like-cnt${post.postId}" class="text-like" onclick="show_like_modal(${post.postId},'${loginNickname}')">좋아요 ${likeBook.likeCnt}개</p>
                        <p class="show_all_comment" id="${post.postId}" onclick="show_all_comment(${post.postId},'${loginNickname}')">전체 댓글 보기..</p>
                    </c:if>
                </c:forEach>
                <div class="comment_box" id="comment_box${post.postId}">
                   <%-- <c:forEach var="comment" items="${postMap.commentList}">--%>
                        <%--<c:if test="${comment.postId == post.postId}">--%>
<%--                            <div>
                                <div class="comment">
                                    <b class="writer">${comment.user_Nickname}&nbsp;</b>
                                    <span class="post-comment">
                                            ${comment.postComment}
                                    </span>
                                </div>
                            </div>--%>
<%--                        </c:if>
                    </c:forEach>--%>
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
