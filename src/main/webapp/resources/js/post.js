
// 이미지 슬라이드
$(document).ready(function(){
    $(".slider").bxSlider({
        adaptiveHeight: true
    });

});

let commentNo = [];
let commentTarget;
// 좋아요 버튼
function likeClick(like,postId,loginNickname, target, likeCnt) {
    $.ajax({
        type:"get",
        url:"/main/post/heartPush.do",
        data: {
            check : like,
            postId : postId,
            loginNickname : loginNickname
        },
        contentType : "application/json",
        success:function(res){
            if(like == 1){
                //const icon_like = document.getElementById("icon-like-pushed");
                target.innerHTML = '<i class="fa-regular fa-heart"></i>';
                target.style.margin = '0 15px 0 0';
                target.style.fontSize = '1.5rem';
                target.style.color = 'black';
                target.setAttribute("id", "icon-like");
                target.setAttribute('onclick','likeClick(0,'+postId+',"'+loginNickname+'", this,'+Number((likeCnt)-1)+')');
                const like_cnt = document.querySelector("#like-cnt"+postId);
                like_cnt.innerHTML = '좋아요 '+Number((likeCnt)-1)+'개';
                //target.parentElement.parentElement.parentElement.children[5].innerHTML = '좋아요 '+Number((likeCnt)-1)+'개';
                console.log(Number(likeCnt-1));
            }else if(like == 0) {
                //const icon_like = document.getElementById("icon-like");
                target.innerHTML = '<i class="fa-solid fa-heart"></i>';
                target.style.color = 'red';
                target.style.margin = '0 15px 0 0';
                target.style.fontSize = '1.5rem';
                target.setAttribute("id", "icon-like-pushed");
                let like_plus = Number(likeCnt)+Number(1);
                target.setAttribute('onclick','likeClick(1,'+postId+',"'+loginNickname+'", this,'+like_plus+')');
                const like_cnt = document.querySelector("#like-cnt"+postId);

                like_cnt.innerHTML = '좋아요 '+like_plus+'개';
            }
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("통신 실패")
        }
    });
}

// 북마크 버튼
function bookClick(book,postId,loginNickname,target){
    $.ajax({
        type:"get",
        url:"/main/post/bookPush.do",
        data: {
            check : book,
            postId : postId,
            loginNickname : loginNickname
        },
        contentType : "application/json",
        success:function(res){
            if(book == 1){
                target.innerHTML = '<i class="fa-regular fa-bookmark"></i>';
                target.style.margin = '0 15px 0 0';
                target.style.fontSize = '1.5rem';
                target.setAttribute("class", "icon");
                target.setAttribute('onclick','bookClick(0,'+postId+',"'+loginNickname+'", this)');
            }else if(book == 0) {
                target.innerHTML = '<i class="fa-solid fa-bookmark"></i>';
                target.style.margin = '0 15px 0 0';
                target.style.fontSize = '1.5rem';
                target.setAttribute("class", "icon-book-pushed");
                target.setAttribute('onclick','bookClick(1,'+postId+',"'+loginNickname+'", this)');
            }
        },
        error:function (XMLHttpRequest, textStatus, errorThrown){
            alert("통신 실패")
        }
    });
}

// 댓글달기 & 댓글 저장
function addComment(postId,loginNickname){
    // 댓글 가져오기
    const commentInput = document.querySelector("#inputComment"+postId);
    let comment = commentInput.value

    if(comment == "" || comment.length == 0){
        alert("댓글을 입력해 주세요")
    }else {
        $.ajax({
            type: "get",
            url: "/main/post/inputComment.do",
            contentType: "application/json",
            data: {
                comment: comment,
                postId: postId,
                loginNickname: loginNickname,
                parentNo : 0
            },
            success: function (res) {
                const commentArea = document.getElementById("comment_box"+postId);
                console.log(commentArea);
                let div = document.createElement('div');
                div.style.margin ='0 15px 5px 15px';
                div.style.display ='flex';
                let writer = document.createElement('b');
                let post_comment = document.createElement('span');

                writer.innerHTML = loginNickname+'&nbsp;'
                writer.setAttribute('class','writer');
                writer.style.fontWeight = 'bold';
                post_comment.innerText = comment;
                post_comment.setAttribute('class','post_comment');
                post_comment.style.wordBreak = 'break-all';

                div.appendChild(writer);
                div.appendChild(post_comment);

                commentArea.appendChild(div);

                commentInput.value = "";
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("댓글 입력 실패")
            }
        });
    }
}

// 댓글 모달창 -> 댓글 달기
function add_modal_Comment(postId,loginNickname){
    // 댓글 가져오기
    const commentInput = document.querySelector("#modal_inputComment"+postId);
    let comment = commentInput.value
    if(comment == "" || comment.length == 0){
        alert("댓글을 입력해 주세요")
    }else {
        $.ajax({
            type: "get",
            url: "/main/post/inputComment.do",
            contentType: "application/json",
            data: {
                comment: comment,
                postId: postId,
                loginNickname: loginNickname,
                parentNo : 0
            },
            success: function (res) {
               /* const commentArea = document.getElementById("comment"+postId);
                console.log(commentArea);
                let div = document.createElement('div');
                div.style.margin ='0 15px 5px 15px';
                div.style.display ='flex';
                let writer = document.createElement('b');
                let post_comment = document.createElement('span');

                writer.innerHTML = loginNickname+'&nbsp;'
                writer.setAttribute('class','writer');
                post_comment.innerText = comment;
                post_comment.setAttribute('class','post_comment');
                post_comment.style.wordBreak = 'break-all';

                div.appendChild(writer);
                div.appendChild(post_comment);

                commentArea.appendChild(div);*/

                show_all_comment(postId, loginNickname);
                commentInput.value = "";
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("댓글 입력 실패")
            }
        });
    }
}


// 댓글 상자 보이기, 숨기기
/*function show_comment_box(postId,num,target){
    const commentArea = document.getElementById("comment_box"+postId);
    if(num == 0){
        commentArea.setAttribute('class','comment_box');
        commentArea.setAttribute("id","comment_box"+postId);
        target.setAttribute('onclick','show_comment_box('+postId+',1,this)');
        //commentArea.setAttribute('onclick','show_comment_box('+postId+',1)');
        commentArea.style.display ='block';
    }else if(num == 1){
        commentArea.setAttribute('class','comment_box');
        commentArea.setAttribute("id","comment_box"+postId);
        target.setAttribute('onclick','show_comment_box('+postId+',0,this)');
        //commentArea.setAttribute('onclick','show_comment_box('+postId+',0)');
        commentArea.style.display ='none';
    }
}*/

// 더보기 메뉴
function show_menu(postId,num) {
    const wrap_menu = document.getElementById("menu_box"+postId);
    const icon_more = document.getElementById("more"+postId);
    if(num == 0){
        wrap_menu.style.display = 'block';
        icon_more.setAttribute('class','icon-more');
        icon_more.setAttribute('onclick','show_menu('+postId+',1)');
    }else if(num == 1){
        wrap_menu.style.display = 'none';
        icon_more.setAttribute('class','icon-more');
        icon_more.setAttribute('onclick','show_menu('+postId+',0)');
    }

}

// 더보기메뉴 - 게시물 삭제
function deletePost(url,postId) {
    //location.href = "/post/deletePost.do?postId="+postId;
    let form = document.createElement('form');
    form.setAttribute('method','post');
    form.setAttribute('action',url);

    let postIdInput = document.createElement('input');
    postIdInput.setAttribute('type','hidden');
    postIdInput.setAttribute('name','postId');
    postIdInput.setAttribute('value',postId);

    form.appendChild(postIdInput);
    document.body.appendChild(form);
    form.submit();
}


// 전체댓글 보기 (댓글 모달창 열기)
function show_all_comment(postId, loginNickname) {
    const modal = document.querySelector("#modal"+postId);
    const body = document.getElementsByTagName('body');
    body[0].style.overflow = 'hidden';
    modal.style.display = 'flex';
    let replyCnt = 0;

    // 해당 게시물의 댓글 정보 가져오기
    $.ajax({
        type:"get",
        url:"/main/post/getComment.do",
        contentType: "application/json",
        async: false,
        data :{
            postId : postId
        },
        success: function (data) {
            const modal_body = document.querySelector("#modal_body" + postId);
            let content = "";
            /*let replyCommentCtn = [];*/
            for (let i = 0; i < data.length; i++) {
                //console.log(data[i].user_Nickname);
                if(data[i].level == 1) {
                    replyCnt = reply_comment_cnt(data[i].commentId);
                    let comment_content = "<div id='comment" + postId + "'>";
                    comment_content += "<div class='modal_comment_box'>";
                    comment_content += "<div class='modal_comment_profileImage'>";
                    comment_content += "<a href='/main/profile/userProfile.do?userNickname=" + data[i].user_Nickname + "'>";
                    comment_content += "<img class='modal_profile_32px'";
                    comment_content += "src='/main/post/profileImageDownload.do?userNickname="+data[i].user_Nickname+"'";
                    comment_content += "alt='프로필 이미지'/>";
                    comment_content += "</a>";
                    comment_content += "</div>";
                    comment_content += "<div class='modal_comment_content'>";
                    comment_content += "<span class='modal_comment_nickname'>"+data[i].user_Nickname+"&nbsp;</span>";
                    comment_content += "<span class='modal_comment'>" + data[i].postComment + "</span>";
                    comment_content += "<div style='display: flex'><p class='modal_replycomment'";
                    // 로그인닉네임과 작성자가 같으면 휴지통 아이콘
                    if(data[i].user_Nickname === loginNickname){
                        comment_content += "onClick='reply_comment_btn(\"" + data[i].user_Nickname + "\"," + postId + "," + data[i].commentId + ",\"" + loginNickname + "\" ,"+data[i].level+")'>답글달기..";
                        comment_content += "</p><i onclick='deleteComment("+data[i].commentId+","+postId+",\""+loginNickname +"\")' class=\'fa-solid fa-trash-can\' style='margin-left: 10px;color: #A9A9A9;font-size: 13px;padding-top: 4px;cursor: pointer;'></i></div>";
                    }else{
                        comment_content += "onClick='reply_comment_btn(\"" + data[i].user_Nickname + "\"," + postId + "," + data[i].commentId + ",\"" + loginNickname + "\" ,"+data[i].level+")'>답글달기..</p>";
                    }
                    // 답글이 있으면 답글 갯수 출력
                    if(replyCnt > 1 ){
                        comment_content += "<p id='more_comment" + data[i].commentId + "'";
                        comment_content += "class='show_more_reply_comment'";
                        comment_content += "onClick='show_more_reply_comment("+data[i].commentId+",this,"+(replyCnt-1)+")'>";
                        comment_content += "___ 답글 보기("+(replyCnt-1)+"개) </p>";
                        comment_content += "</div> </div> </div>";
                    }else{
                        comment_content += "</div> </div> </div>";
                    }
         /*           if(commentNo.includes(data[i].commentId)){
                        replyCommentCtn.push(replyCnt);
                    }*/
                    content += comment_content;
                }else {
                    let comment_content = "<div id='reply" + postId + "'>";
                    comment_content += "<div id='reply" + data[i].commentId + "' class='modal_comment_replybox' style='margin-left: 50px'>";
                    comment_content += "<div class='modal_comment_profileImage'>";
                    comment_content += "<a href='/main/profile/userProfile.do?userNickname=" + data[i].user_Nickname + "'>";
                    comment_content += "<img class='modal_profile_32px'";
                    comment_content += "src='/main/post/profileImageDownload.do?userNickname=" + data[i].user_Nickname + "'";
                    comment_content += "alt='프로필 이미지'/>";
                    comment_content += "</a>";
                    comment_content += "</div>";
                    comment_content += "<div class='modal_comment_content'>";
                    comment_content += "<span class='modal_comment_nickname'>" + data[i].user_Nickname + "&nbsp;</span>";
                    for(let j=0;j<data.length;j++){
                        if(data[i].parentNo === data[j].commentId){
                            comment_content += "<span style='color: #3f729b'>@"+data[j].user_Nickname+"</span>";
                        }
                    }
                    comment_content += "<span class='modal_comment'>" + data[i].postComment + "</span>";
                    comment_content += "<p class='modal_replycomment' onclick='reply_comment_btn(\"" + data[i].user_Nickname + "\"," + postId + "," + data[i].commentId + ",\"" + loginNickname + "\")'>답글달기..</p>";
                    comment_content += "</div></div></div>";
                    content += comment_content;
                }
            }

            modal_body.innerHTML = content;

            for(let i=0;i<commentNo.length;i++){
               const targetTest = document.querySelector("#more_comment"+commentNo[i]);
                var thisTarget = document.querySelector("#more_comment"+commentNo[i]);
                show_more_reply_comment(commentNo[i],thisTarget /*replyCommentCtn[i]-1*/);
                console.log(commentNo[i]);
                //console.log("commentNO : " + commentNo[i]);

             }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("댓글 가져오기 실패")
        }
    });
}


// 모달창 닫기
function close_modal(postId) {
    const modal = document.querySelector("#modal" + postId);
    const body = document.getElementsByTagName('body');
    body[0].style.overflow = 'auto';
    modal.style.display = 'none';
    commentNo =[];
}

// 전체 댓글 모달창 열기
// 답글 달기...
function reply_comment_btn(nickName, postId, commentId, loginNickname, level) {
    if(level === 1){
        commentNo.push(commentId);
        /*replyCommentCtn.push(replyCnt);*/

    }
    $('#modal_inputComment' + postId).focus();
    $('#modal_inputComment' + postId).val('');
    $('#modal_inputComment' + postId).val(' @' + nickName + ' ');

    const btn_chat = document.querySelector("#btn-chat" + postId);
    btn_chat.setAttribute('id', 'btn-chat' + postId);
    btn_chat.setAttribute('class', 'btn-chat');
    btn_chat.setAttribute('onclick', 'add_reply_comment(' + postId + ',"' + loginNickname + '",' + commentId + ',"' + nickName + '")');
    }


// 답글 게시
    function add_reply_comment(postId, loginNickname, commentId, nickName, replyCnt) {
/*    if(commentNo.includes(commentId)){
        replyCommentCtn.push(replyCnt+1);
    }*/
        const reply_comment = document.querySelector("#modal_inputComment" + postId);
        // 닉네임 기준으로 댓글 자르기
        let allcomment = reply_comment.value;
        var split_comment = allcomment.split(nickName);

        if(split_comment.length == 1) {
            add_modal_Comment(postId, loginNickname);
        } else {
            let comment = split_comment[1];
            if (split_comment[1] === '' || split_comment[1] === ' ') {
                alert('댓글을 입력해 주세요');
            } else {
                $.ajax({
                    type: "get",
                    url: "/main/post/inputComment.do",
                    contentType: "application/json",
                    data: {
                        comment: comment,
                        postId: postId,
                        loginNickname: loginNickname,
                        parentNo: commentId,

                    },
                    success: function (res) {
                        show_all_comment(postId, loginNickname);
                        reply_comment.value = "";
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert('댓글 입력 실패');
                    }
                });
            }
        }
    }
    // 답글의 갯수만 가져오는 메서드
    function reply_comment_cnt(commentId){
        let result = 0;
        $.ajax({
            type: "get",
            url: "/main/post/getReplyComment.do",
            contentType: "application/json",
            async: false,
            data: {
                commentId: commentId
            },
            success: function (data) {
                result = data.length;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert('가져오기 실패');
            }
        });
        return result;
    }


// __ 답글 보기 클릭
    function show_more_reply_comment(commentId, target) {
    /*commentNo = commentId;*/
    if(!commentNo.includes(commentId)){
        commentNo.push(commentId);
        /*commentTarget = target;*/
    }
    let replyCnt = reply_comment_cnt(commentId);
        $.ajax({
            type: "get",
            url: "/main/post/getReplyComment.do",
            contentType: "application/json",

            data: {
                commentId: commentId
            },
            success: function (res) {
                for (let i = 1; i < res.length; i++) {
                    /*    const reply = $('#reply'+i);*/
                    const reply = document.getElementById("reply" + res[i]);
                    reply.style.display = 'flex';
                }
                target.innerHTML = '___ 답글 숨기기('+(replyCnt-1)+'개)';
                target.setAttribute('class', 'show_more_reply_comment');
                target.setAttribute('id', 'more_comment' + commentId);
                target.setAttribute('onclick', 'hide_more_reply_comment(' + commentId + ',this,)');
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert('가져오기 실패');
            }
        });
    }

// ___답글 숨기기 클릭
    function hide_more_reply_comment(commentId, target) {
        let replyCnt = reply_comment_cnt(commentId);
        if(commentNo.includes(commentId)){
            console.log("포함됨")
            commentNo.splice(commentNo.indexOf(commentId),1);
            for(let i=0;i<commentNo.length;i++){
                console.log(commentNo[i]);
            }
            /*commentTarget = target;*/
        }
    // commentNo =commentId;
        $.ajax({
            type: "get",
            url: "/main/post/getReplyComment.do",
            contentType: "application/json",
            data: {
                commentId: commentId
            },
            success: function (res) {
                for (let i = 1; i < res.length; i++) {
                    /*    const reply = $('#reply'+i);*/
                    const reply = document.getElementById("reply" + res[i]);
                    reply.style.display = 'none';
                }
                target.innerText = '___ 답글 보기('+(replyCnt-1)+'개)';
                target.setAttribute('class', 'show_more_reply_comment');
                target.setAttribute('id', 'more_comment' + commentId);
                target.setAttribute('onclick', 'show_more_reply_comment(' + commentId + ',this,)');

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert('가져오기 실패');
            }
        });
    }

// 좋아요 목록 닫기 버튼
    function close_like_modal(postId) {
        const modal = document.querySelector("#like_modal" + postId);
        const body = document.getElementsByTagName('body');
        body[0].style.overflow = 'auto';
        modal.style.display = 'none';
    }

// 좋아요 모달창 -> 팔로우 클릭
    function follow(loginId, followId, target) {
        $.ajax({
            type: "get",
            url: "/main/post/follow.do",
            contentType: "application/json",
            data: {
                loginId: loginId,
                followId: followId
            },
            success: function (res) {
                target.style.color = 'black';
                target.style.backgroundColor = '#A9A9A9';
                target.value = '팔로잉';
                target.setAttribute('onclick', 'following("' + loginId + '","' + followId + '",this)');
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("팔로우 실패!!")
            }
        });
    }

// 좋아요 모달 -> 팔로우 취소
    function following(loginId, followingId, target) {
        $.ajax({
            type: "get",
            url: "/main/post/deleteFollow.do",
            contentType: "application/json",
            data: {
                loginId: loginId,
                followingId: followingId
            },
            success: function (res) {
                target.style.color = 'white';
                target.style.backgroundColor = '#458eff';
                target.value = '팔로우';
                target.setAttribute('onclick', 'follow("' + loginId + '","' + followingId + '",this)');
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("팔로우 취소 실패!!")
            }
        });
    }

    function deleteComment(commentId,postId, loginNickname) {
        console.log(commentId);
        if(confirm("댓글을 삭제 하시겠습니까?") === true){
            $.ajax({
                type:"get",
                url:"/main/post/deleteComment.do",
                contentType:"application/json",
                data: {
                    commentId : commentId
                },
                success:function (res){
                    show_all_comment(postId, loginNickname);
                },
                error:function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("댓글 삭제 실패");
                }
            });
        }else{
            return;
        }

    }

    // ######################################################################################### 선준

    let searchInput = $('#searchInput');
    let searchPoint = $('#searchPoint');
    let searchBackground = $('#searchBackground');
    $(document).ready(function() {
        // 카테고리 태그 클릭 이벤트
        $('.M1-1').click(function() {

            searchPoint.show();

            // 클릭된 태그의 left 위치와 너비를 가져옵니다.
            let tagLeft = $(this).position().left;
            let tagWidth = $(this).outerWidth();

            // 서치포인트의 너비를 가져옵니다.
            let searchPointWidth = searchPoint.outerWidth();

            // 서치포인트를 태그의 중앙으로 이동시키기 위해 계산합니다.
            let newLeft = tagLeft + tagWidth/2 - searchPointWidth/2;

            // 서치포인트의 위치를 업데이트합니다.
            $('#searchPoint').css('left', newLeft);
        });

        // 전체 문서에서 클릭 이벤트를 감지합니다.
        $(document).click(function(event) {
            // searchBackground가 현재 보여지는 상태인지 확인합니다.
            let isSearchBackgroundVisible = searchBackground.css('display') !== 'none';

            // searchBackground가 보여지고 있고, 클릭된 요소가 searchC 내부나 searchC 자체가 아닐 경우에만 동작합니다.
            if (isSearchBackgroundVisible) {
                let isClickInsideSearchC = $('#searchC').is(event.target) || $('#searchC').has(event.target).length > 0;

                if (!isClickInsideSearchC) {
                    $('#searchBackground').hide();
                }
            }
        });

        // searchC 내부에서의 클릭이 문서의 다른 부분으로 이벤트를 전파하는 것을 방지합니다.
        $('#searchC').click(function(event) {
            event.stopPropagation();
        });

        // 검색 아이콘을 클릭하면 searchBackground를 보여줍니다.
        $('.fa-magnifying-glass').click(function() {
            $('#searchBackground').show();
            event.stopPropagation(); // 이벤트 버블링 방지
        });

        let searchBtn = $('#searchBtn');
        let cate1 = $('#cate1');
        let cate2 = $('#cate2');
        let cate3 = $('#cate3');
        let cateName = "유저";

        cate1.on('click', function (){
            cateName = "유저";
        });
        cate2.on('click', function (){
            cateName = "게시판";
        });
        cate3.on('click', function (){
            cateName = "태그";
        });

        searchInput.keypress(function(event) {
            // event.which === 13은 엔터키의 키 코드입니다.
            if (event.which === 13) {
                event.preventDefault(); // 폼의 기본 제출 이벤트를 방지합니다.
                userSearch();
            }
        });

        function userSearch(){
            let searchContent = $('#searchInput').val();

            if(cateName === "유저"){
                $.ajax({
                    type: "get",
                    url: "/main/post/userSearch.do",
                    dataType: "json", // 응답을 JSON으로 받음을 명시적으로 지정
                    data: {
                        userName: searchContent,
                    },
                    success: function (res) {
                        // 성공 시, searchM2의 내용을 업데이트
                        console.log(res);
                        let htmlContent = '';
                        res.forEach(function(user) {
                            htmlContent +=
                                '<div class="searchResults">' +
                                '<div class="searchResultContainer">' +
                                '<div class="srA1">' +
                                '<i class="fa-sharp fa-solid fa-circle-user fa-5x"></i>' +
                                '</div>' +
                                '<div class="srA2">' +
                                '<div class="srB1">' +
                                '<h6 class="resultName">' + user.userName + '</h6>' +
                                '<div class="resultContents">' + user.userId + '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>' +
                                '</div>';
                        });
                        // 결과 HTML을 searchM2 요소에 추가
                        $('#searchM2').html(htmlContent);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert("검색 실패!!");
                    }
                });
            }
        }

        searchBtn.on('click', function (){
            userSearch()
        });

    });