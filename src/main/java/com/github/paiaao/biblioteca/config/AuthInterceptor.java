package com.github.paiaao.biblioteca.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        if (uri.startsWith("/auth") || uri.startsWith("/css") || uri.startsWith("/js")) {
            return true;
        }

        if (session == null || session.getAttribute("funcionarioLogado") == null) {
            response.sendRedirect("/auth/login");
            return false;
        }

        return true; // usuário logado continua a requisição
    }
}
