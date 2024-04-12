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
                '<a href="/main/profile/userProfile.do?userNickname=' + user.userNickname + '">' +
                '<img style="width: 100px; height: 100px; border-radius: 50%;" src="/main/profile/download.do?imageFileName=' + user.profileImg + '&accountId=' + user.accountID+ '">' +
                '</a></div>' +
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