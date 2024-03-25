package org.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// 필터 기능 ( /main 과, /main 이후의 경로의 요청은 세션에서 권한을 확인
// 비회원은 / 로 회원은 메인 페이지로, 관리자는 관리 페이지로 이동될 수 있도록
@WebFilter({"/main/*", "/main"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필요한 경우 초기화 작업을 수행할 수 있습니다.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 세션에서 ROLE 값을 가져옵니다.
        String ROLE = (String) request.getSession().getAttribute("ROLE");

        // ROLE 값이 null 인지 확인하고 "/"로 리다이렉트합니다.
        if (ROLE == null) {
            response.sendRedirect("/?warning=loginRequired");
            return;
        }

        // ROLE 값이 null 이 아니라면 요청을 계속 진행합니다.
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 필요한 경우 리소스 해제 작업을 수행할 수 있습니다.
    }
}
