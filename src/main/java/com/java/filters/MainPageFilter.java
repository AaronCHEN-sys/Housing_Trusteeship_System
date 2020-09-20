package com.java.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:	   main.jsp过滤器<br/>
 * Date:     0019, September 19 20:13 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@WebFilter("/*")
public class MainPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求的资源
        String requestURI = request.getRequestURI();
        String username = (String) request.getSession().getAttribute("username");
        if (requestURI != null && requestURI.endsWith("main.jsp") && username == null) {
            response.sendRedirect("/pages/admin/login.jsp");
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
