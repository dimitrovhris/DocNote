package com.example.docnote.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class LoginPageFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                &&(((HttpServletRequest)request).getRequestURI().equals("/")
                || ((HttpServletRequest)request).getRequestURI().equals("/user/login")
                || ((HttpServletRequest)request).getRequestURI().equals("/user/register"))){
            System.out.println("user is authenticated but trying to access login page, redirecting to /");
            ((HttpServletResponse)response).sendRedirect("/home");
        }
        chain.doFilter(request, response);
    }

}
