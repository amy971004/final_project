<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="org.example.util.MyBatisUtil"%>
<%@ page import="org.apache.ibatis.session.SqlSession"%>
<%@ page import="org.example.member.dto.MemberDTO"%>

<%
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    List<MemberDTO> userList = sqlSession.selectList("mapper.member.selectAllUsers");
    request.setAttribute("userList", userList); // userList를 request 속성에 추가
    sqlSession.close();
%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<style>
    #userTable {
        border: black 2px solid;
        width: 1400px;
        height: 30px;
    }
    #userTableTitle {
        height: 50px;
    }
</style>
<body>
<h3>회원관리 페이지</h3>

<p> pageContext : ${pageContext}
<p> realPath : ${realPath}
<p> Context Path: <%= request.getContextPath() %>
<p> Request URI: <%= request.getRequestURI() %><br>

<table id="userTable" border="1">
<tr id="userTableTitle">
    <th>프로필</th>
    <th>식별자 ID</th>
    <th>아이디</th>
<%--    <th>비밀번호</th>--%>
    <th>닉네임</th>
    <th>이름</th>
    <th>이메일</th>
    <th>생일</th>
    <th>핸드폰번호</th>
    <th>가입일</th>
    <th>삭제</th>
</tr>

<c:forEach var="user" items="${userList}">
    프로필 이미지 경로: ${realPath}
    디비 경로 : ${user.profileImg}
    <tr>
        <td><img src="${contextPath}/profile/download.do?imageFileName=${user.profileImg}&userId=${user.userId}" alt="프로필 이미지" style="width: 100px; height: 100px;"/></td>
        <td>${user.accountID}</td>
        <td>${user.userId}</td>
<%--        <td>${user.userPw}</td>--%>
        <td>${user.userNickname}</td>
        <td>${user.userName}</td>
        <td>${user.userEmail}</td>
        <td>${user.userBirthday}</td>
        <td>${user.userPhoneNumber}</td>
        <td>${user.introduction}</td>
        <td><button onclick="deleteUser('${user.userId}')">삭제</button></td>
    </tr>
</c:forEach>


</table>

<script>
    function deleteUser(userId) {
        const confirmation = confirm('해당 회원을 삭제하시겠습니까?');
        if (confirmation) {
            $.ajax({
                url: '/main/deleteUser',
                type: 'POST',
                data: {userId: userId},
                success: function(response) {
                    if (response === 'SUCCESS') {
                        // 삭제 성공 시 페이지 새로고침 또는 사용자에게 알림
                        alert('회원이 성공적으로 삭제되었습니다.');
                        location.reload(); // 페이지를 새로고침하여 변경사항 반영
                    } else {
                        // 서버 측에서 삭제 실패 응답을 받았을 때
                        alert('삭제에 실패하였습니다. 다시 시도해주세요.');
                    }
                },
                error: function(xhr, status, error) {
                    // 에러 처리
                    alert('삭제 중 오류가 발생했습니다. 다시 시도해주세요.');
                }
            });
        }
    }
</script>

    </body>
</html>
