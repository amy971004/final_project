/* 업로드 확인 여부 */
function upload_check(){
    // 업로드 누를 시
    if(confirm('게시물을 수정하시겠습니까?') === true){ // 확인 누를 시
        let frm = document.getElementById("uploadFrm");
        // 해시태그
        let hashtags = [];
        $('.hashTag').each(function (){
            let hashtag = $(this).text().trim();
            hashtags.push(hashtag.substring(1));
        })
        let hashtagString = hashtags.join(',');
        let hashtag = document.getElementById("hashTagg");
        hashtag.value = hashtagString;

        frm.submit();
    }
}
// 글자 수 체크
function count(){
    let content = $('#content_input').val();
    if(content.length <= 1500){
        $('.text_count').text(content.length + '/1500');
    }else{
        $('.text_count').value = content.substring(0,1500);
    }
}

/* 해시태그 추가 */
let cnt = 0;
function hashTag(){
    let input = document.getElementById('tag');
    $('.hashtag_box').append("<span class='hashtag'><span class='hashTag'>"+"#"+input.value+"</span><button class='del_btn' type='button'>X</button></span>")
    input.value = "";
    cnt++;
}
/* 해시태그 삭제 */
$(document).on("click",".del_btn",function (e){
    let target = e.target;
    $(target).parent('.hashtag').remove();
})

/* 취소 확인 여부 */
function cancel(){
    // 확인 클릭 시 메인 페이지로 이동
    if(confirm('취소하시겠습니까?') === true){
        location.href = "/main/post/mainPost.do";
    }// 취소 클릭 시 계속 작성
}

/* 이미지 미리보기 슬라이드 */
$(document).ready(function (){
    $( '.slider' ).slick( {
        slidesToShow: 1,
        slidesToScroll: 1,
        dots:true,
        speed: 700,
        centerMode:true,
        centerPadding: '0px',
        infinite: false,
    } );
});