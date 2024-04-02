<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform" xmlns:th="http://www.w3.org/1999/xhtml" xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<link href="../../resources/css/profile.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="../../resources/css/main.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="../../resources/css/reset.css">
<link rel="stylesheet" href="../../resources/css/common.css">
<link rel="stylesheet" href="../../resources/css/style.css">
<link rel="stylesheet" href="../../resources/css/nav.css">
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<style>
    /* modal */
    .modal {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 9999;
        background: rgba(0, 0, 0, 0.5);
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .modal-content {
        position: relative;
        width: 400px;
        height: 500px;
        background: white;
        padding: 24px 16px;
        border-radius: 4px;
        overflow-y: auto;

    }

    .modal_table{
        width:100%;
        height: 300px;

    }
    .modal-title {
        display: inline-block;
        font-size: 24px;
        font-weight: bold;
        text-align:left;
    }
    #modal-close {
        position: absolute;
        top: 24px;
        right: 16px;
        cursor: pointer;
    }
    #modal_userImg{
        width:50px;
        height:50px;
        border-radius: 75%;
    }
    #modal_userID{

        width:200px;
    }
    #modal_userFollow{
        margin:10px;
        text-align: right;
        right: 16px;
    }
</style>

<body>


<div class="logo">
    <a href="#" class="no-underline" style="padding-top: 20px; font-size: 25px">L</a>
    <!-- 홈 -->
    <a class="no-underline"><i onclick="location.href='http://localhost:8081/main'" class="fa-solid fa-house"></i></a>
    <!-- 검색 -->
    <a href="#" class="no-underline"><i class="fa-solid fa-magnifying-glass "></i></a>
    <!-- 알림 -->
    <a href="#" class="no-underline"><i class="fa-solid fa-bell "></i></a>
    <!-- 북마크 -->
    <a href="#" class="no-underline"><i class="fa-regular fa-bookmark "></i></a>
    <!-- 업로드 -->
    <a class="no-underline"><i onclick="location.href='http://localhost:8081/main/post/uploadPost.do'" id="uploadBtn" class="fa-solid fa-plus"></i></a>
    <!-- 내프로필 -->
    <a class="no-underline"><i onclick="location.href='http://localhost:8081/main/profile/profileView.do'" class="fa-regular fa-user"></i></a>
    <!-- 로그아웃 -->
    <a class="no-underline"><i id="logoutBtn" class="fa-solid fa-arrow-right-from-bracket"></i></a>
</div>
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
                        <img src="${contextPath}/main/profile/download.do?imageFileName=${profile.profileImg}&userId=${profile.userId}"  alt=""/>
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


                <ul class="profile-count">
                    <li><span class="profile-stat-count">${postCount}</span> posts</li>
                    <li id="follower"><span  class="profile-stat-count">188</span> followers</li>
                    <li><span class="profile-stat-count">206</span> following</li>
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



    <div class="container">
        <div class="gallery">
            <c:forEach var="post" items="${profileMap.postDTO}">
                <div class="gallery-item" tabindex="0" data-postId="${post.postId}">
                    <c:forEach var="image" items="${profileMap.imageDTO}">
                        <c:if test="${post.postId eq image.postId}">
                            <img src="${contextPath}/main/post/imageDownload.do?imageFileName=${image.fileName}&postId=${post.postId}" class="gallery-image" alt="">
                        </c:if>
                    </c:forEach>
                    <div class="gallery-item-info">
                        <p class="location-feed">${post.content}</p>
                        <ul>
                            <c:set var="likeCount" value="0" />
                            <c:forEach var="like" items="${profileMap.likeDTO}">
                                <c:if test="${post.postId eq like.postId}">
                                    <c:set var="likeCount" value="${likeCount + 1}" />
                                </c:if>
                            </c:forEach>
                            <li class="gallery-item-likes" data-postId="${post.postId}"><span class="visually-hidden">Likes: </span>
                                <i class="fas fa-heart" aria-hidden="true"></i>
                                <c:choose>
                                    <c:when test="${likeCount == null}}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${likeCount}
                                    </c:otherwise>
                                </c:choose>
                            </li>

                            <c:set var="commentCount" value="0" />
                            <c:forEach var="comment" items="${profileMap.commentDTO}">
                                <c:if test="${post.postId eq comment.postId}">
                                    <c:set var="commentCount" value="${commentCount + 1}" />
                                </c:if>
                            </c:forEach>
                            <li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true">

                            </i>
                                <c:choose>
                                    <c:when test="${commentCount == null}}">
                                        0
                                    </c:when>
                                    <c:otherwise>
                                        ${commentCount}
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:forEach>
            <!-- End of gallery -->
        </div>
        <!-- End of container -->
    </div>


</main>
<%--팔로우 모달창--%>
<div class="modal fade" id="followModal" style="display: none;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <%--모달창의 header 부분에 해당한다.--%>
                <h4 class="modal-title"></h4>
                <button id="modal-close" type="button" class="close">×</button>
            </div>
            <hr>
            <div class="modal-body">
                <%--모달창의 가운데 내용이 들어가는 곳이다.--%>
                <table class="modal_table">


                </table>
            </div>
            <div id="count" value="1"></div>
        </div>
    </div>
</div>

<script>
    /* 팔로워 모달창*/
    /*팔로워 버튼 클릭*/
    const open = document.getElementById("follower");
    const close = document.getElementById("modal-close");
    const modal = document.querySelector(".modal");

    open.addEventListener('click', function (){
        $('.modal-title').text("팔로우");
        modal.style.display = "flex";
    });

    close.addEventListener('click', function (){
        modal.style.display = "none";
    });

    /* 좋아요 모달창*/
    $('.gallery-item-likes').on('click', function (){
        const postId = $(this).closest('.gallery-item')[0].getAttribute('data-postId');
        $('.modal-title').text("좋아요");
        console.log("postId : "+ postId);
        $.ajax({
            url: '/main/profile/likes.do',
            type: 'POST',
            data: { postId: postId }, // 서버에 보낼 데이터
            dataType: 'json', // 응답 데이터 형식
            success: function(response) {
                // 성공적으로 데이터를 받아왔을 때의 동작
                // 받아온 데이터를 사용하여 모달 창에 좋아요를 누른 사용자의 리스트를 표시하는 함수 호출
                displayLikesModal(response);
            },
            error: function(xhr, status, error) {
                // 에러 발생 시 처리
                console.error('데이터를 불러오는 중 에러 발생:', error);
            }
        });
        modal.style.display = "flex";
    });

    close.addEventListener('click', function (){
        modal.style.display = "none";
    });

    const contextPath = "${contextPath}";

    function displayLikesModal(likesData) {
        const modalBody = document.querySelector('.modal_table');
        let likesListHTML = '';
        likesData.forEach(function (like) {
            likesListHTML += '<tr>';
            likesListHTML += '<td style="width:70px;"><img id="modal_userImg" src="' + contextPath + '/main/post/profileImageDownload.do?userNickname=' + like.user_nickname + '" alt=""></td>';
            likesListHTML += '<td id="modal_userID">' + like.user_nickname +'</td>';
            likesListHTML += '<td id="modal_userFollow"><button class="btn btn-outline-primary" value="' + like.user_nickname + '">팔로우</button></td>';
            likesListHTML += '</tr>';

        });
        modalBody.innerHTML = likesListHTML;
    }


    $(document).ready(function() {
        let logoutBtn = $('#logoutBtn');

        logoutBtn.on('click', function (){
            // 로그아웃 여부를 확인하는 경고창 표시
            let confirmLogout2 = confirm('로그아웃 하시겠습니까?');

            if (confirmLogout2) {
                // 사용자가 확인을 선택한 경우에만 로그아웃 처리를 수행함
                $.ajax({
                    url: '/logout.do',
                    type: 'GET',
                    success: function(response) {
                        // 로그아웃 성공 시 콘솔에 메시지 출력
                        console.log('로그아웃 성공');
                        // 로그인 페이지로 리다이렉트
                        window.location.href = '/';
                    },
                    error: function(xhr, status, error) {
                        // 로그아웃 실패 시 콘솔에 메시지 출력
                        console.error('로그아웃 실패:', error);
                    }
                });
            } else {
                // 취소를 눌렀을 때의 동작
                console.log('사용자가 로그아웃을 취소했습니다.');
            }
        });

    });
</script>
</body>
</html>
