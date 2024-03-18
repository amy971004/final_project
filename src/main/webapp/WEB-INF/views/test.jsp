<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL 테스트 페이지</title>
</head>
<body>

<h2>JSTL Core 태그 예시</h2>

<!-- 조건문 예제 -->
<c:if test="${5 > 3}">
    <p>5는 3보다 큽니다.</p>
</c:if>

<!-- 반복문 예제 -->
<c:forEach var="i" begin="1" end="5">
    <p>${i} 번째 반복</p>
</c:forEach>

<h2>JSTL Format 태그 예시</h2>

<!-- 날짜 포맷 예제 -->
<p>현재 날짜: <fmt:formatDate value="${now}" pattern="yyyy-MM-dd 'at' HH:mm:ss" /></p>

<!-- 숫자 포맷 예제 -->
<p>숫자 포맷: <fmt:formatNumber value="123456.789" pattern="#,###.##" /></p>

</body>
</html>
