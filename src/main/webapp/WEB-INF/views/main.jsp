<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

<br>
<p> 본문 - 로그인 성공</p>
<p> 식별자ID - ${sessionScope.accountID}</p>
<p> 권한 - ${sessionScope.RULE}</p>

<%@ include file="footer.jsp"%>
</body>
</html>