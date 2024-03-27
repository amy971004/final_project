
// 이미지 슬라이드
$(document).ready(function(){
    $(".slider").bxSlider({
        adaptiveHeight: true
    });

});

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
                loginNickname: loginNickname
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
function show_all_comment(postId) {
    const modal = document.querySelector(".modal");
    modal.style.display = 'flex';
}