// 이미지 미리보기 + 파일들 삭제
function allDelete(){
    $('#content_input').val('');
    $('.files').val('');
    $('.slider').slick('slickRemove', null, null, true);
    $('.slider').slick('refresh');
}

/* 이미지 보여주기  + 파일 첨부 갯수 제한 */
function readURL(input){
    // 파일 첨부 갯수 제한
    if(input.files.length > 10){
        alert('이미지는 최대 10개 까지 가능합니다.');
        allDelete();
    }
    if(input.files.length === 0){
        $('.slider').slick('slickRemove', null, null, true);
        $('.slider').slick('refresh');
    }

    if(input.files && input.files.length > 0){
        $('.slider').slick('slickRemove', null, null, true);
        $('.slider').slick('refresh');
        for(let i=0; i < input.files.length; i++) {

            if(!input.files[i].type.includes('image/')){
                alert('이미지만 가능합니다.');
                allDelete();
            }
            // 파일 용량 제한
            let imageSize = input.files[i].size;
            let minSize = 5 * 1024;
            let maxSize = 10 * 1024 * 1024;
            if(imageSize < minSize){
                alert('파일의 크기 5KB 이상이어야 합니다.');
                allDelete();
            }
            if (imageSize > maxSize) {
                alert('개별첨부 가능한 파일은 10MB 이하여야 합니다.');
                allDelete();
            }else{
                // 이미지 미리보기
                let reader = new FileReader();
                reader.onload = function (e) {
                    let lastModified = input.files[i].lastModified;
                    $('.slider').slick('slickAdd','<div class="image_div" id="' + lastModified + '"><img id="' + lastModified + '" src="' + e.target.result + '" alt=""/></div></div>');
                }
                reader.readAsDataURL(input.files[i]);
            }
        }
    }
}
// 사진 클릭 시 해당 사진 삭제
$(document).on('click','img', function (e){
    let input = $('.files')[0].files;
    let dataTranster = new DataTransfer();
    let target = e.target;
    let removeSlide = $(target).parent().attr("data-slick-index");
    let removeId = $(target).attr("id");

    for(let i=0; i < input.length; i++){
        let file = input[i];
        if(file.lastModified != removeId){
            dataTranster.items.add(file);
        }
    }
    $('.files')[0].files = dataTranster.files;
    $('.slider').slick('slickRemove', removeSlide);
    $('.slider').slick('refresh');

})
/* 업로드 확인 여부 */
function upload_check(){
    let files = $('.files')[0].files;
    let totalSize = 50 * 1024 * 1024;
    let totalImageSize = 0;
    for(let i=0; i<files.length; i++){
        totalImageSize += files[i].size;
    }
    // 이미지 파일 첨부 확인 여부
    if(files.length === 0){
        alert('이미지 1개 이상 업로드 하세요.');
    }
    // 모든 첨부 파일 용량 제한
    else if(totalImageSize > totalSize){
        alert('모든 첨부파일 용량이 50MB 이하여야 합니다.');
    }else{
        // 업로드 누를 시
        if(confirm('업로드 하시겠습니까?') === true){ // 확인 누를 시
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