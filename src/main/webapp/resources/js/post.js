
// 이미지 슬라이드
$(document).ready(function(){
    $(".slider").bxSlider({
        adaptiveHeight: true
    });

});

let test = 0;
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
                const commentArea = document.getElementById("comment"+postId);
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

                commentArea.appendChild(div);

                commentInput.value = "";

                show_all_comment(postId,loginNickname);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("댓글 입력 실패")
            }
        });
    }
}


// 댓글 상자 보이기, 숨기기
function show_comment_box(postId,num,target){
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
}

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
    
    // 해당 게시물의 댓글 정보 가져오기
    $.ajax({
        type:"get",
        url:"/main/post/getComment.do",
        contentType: "application/json",
        data :{
            postId : postId
        },
        success: function (data) {
            const modal_body = document.querySelector("#modal_body" + postId);
            console.log(data.length);
            let content = "";
            for (let i = 0; i < data.length; i++) {
                //console.log(data[i].user_Nickname);
                if(data[i].level == 1) {
                    console.log("level 1 :"+data[i]);
                    let comment_content = "<div id='comment" + postId + "'>";
                    comment_content += "<div class='modal_comment_box'>";
                    comment_content += "<div class='modal_comment_profileImage'>";
                    comment_content += "<img class='modal_profile_32px'";
                    comment_content += "src='/main/post/profileImageDownload.do?userNickname="+data[i].user_Nickname+"'";
                    comment_content += "alt='프로필 이미지'/>";
                    comment_content += "</div>";
                    comment_content += "<div class='modal_comment_content'>";
                    comment_content += "<span class='modal_comment_nickname'>"+data[i].user_Nickname+"&nbsp;</span>";
                    comment_content += "<span class='modal_comment'>" + data[i].postComment + "</span>";
                    comment_content += "<p class='modal_replycomment'";
                    comment_content += "onClick='reply_comment_btn(\"" + data[i].user_Nickname + "\"," + postId + "," + data[i].commentId + ",\"" + loginNickname + "\")'>답글달기..</p>";
                    comment_content += "<p id='more_comment" + data[i].commentId + "'";
                    comment_content += "class='show_more_reply_comment'";
                    comment_content += "onClick='show_more_reply_comment("+data[i].commentId+",this)'>";
                    comment_content += "___ 답글 보기 </p> </div> </div> </div>";
                    content += comment_content;
                }else {
                    let comment_content = "<div id='reply" + postId + "'>";
                    comment_content += "<div id='reply" + data[i].commentId + "' class='modal_comment_replybox' style='margin-left: 50px'>";
                    comment_content += "<div class='modal_comment_profileImage'>";
                    comment_content += "<img class='modal_profile_32px'";
                    comment_content += "src='/main/post/profileImageDownload.do?userNickname=" + data[i].user_Nickname + "'";
                    comment_content += "alt='프로필 이미지'/>";
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
            console.log("test : " + test);
            modal_body.innerHTML = content;
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
}

// 전체 댓글 모달창 열기
function reply_comment_btn(nickName, postId, commentId, loginNickname) {
    $('#modal_inputComment' + postId).focus();
    $('#modal_inputComment' + postId).val('');
    $('#modal_inputComment' + postId).val(' @' + nickName + ' ');

    const btn_chat = document.querySelector("#btn-chat" + postId);
    btn_chat.setAttribute('id', 'btn-chat' + postId);
    btn_chat.setAttribute('class', 'btn-chat');
    btn_chat.setAttribute('onclick', 'add_reply_comment(' + postId + ',"' + loginNickname + '",' + commentId + ',"' + nickName + '")');
    }

// 답글 게시
    function add_reply_comment(postId, loginNickname, commentId, nickName) {
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
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert('댓글 입력 실패');
                    }
                });
            }
        }
    }

// __ 답글 보기 클릭
    function show_more_reply_comment(commentId, target) {
    test = commentId;
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
                target.innerText = '___ 답글 숨기기';
                target.setAttribute('class', 'show_more_reply_comment');
                target.setAttribute('id', 'more_comment' + commentId);
                target.setAttribute('onclick', 'hide_more_reply_comment(' + commentId + ',this)');
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert('가져오기 실패');
            }
        });
    }

// ___답글 숨기기 클릭
    function hide_more_reply_comment(commentId, target) {
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
                target.innerText = '___ 답글 보기';
                target.setAttribute('class', 'show_more_reply_comment');
                target.setAttribute('id', 'more_comment' + commentId);
                target.setAttribute('onclick', 'show_more_reply_comment(' + commentId + ',this)');
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
        console.log(loginId);
        console.log(followId);
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
        console.log(loginId);
        console.log(followingId);
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

