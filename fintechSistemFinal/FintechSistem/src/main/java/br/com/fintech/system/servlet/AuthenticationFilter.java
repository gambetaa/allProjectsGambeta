package br.com.fintech.system.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
 
        boolean isLoggedIn = (session != null && session.getAttribute("usuario") != null);
        String loginURI = httpRequest.getContextPath() + "/login";
        String cadastroURI = httpRequest.getContextPath() + "/register.jsp"; // Adicione o URI da página de cadastro
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isCadastroRequest = httpRequest.getRequestURI().equals(cadastroURI); // Verifique se é uma requisição para a página de cadastro
 
        if (isLoggedIn || isLoginRequest || isCadastroRequest) {
            chain.doFilter(request, response); 
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response); 
        }
    }
 
    public AuthenticationFilter() {
    }
 
    public void destroy() {
    }
 
    public void init(FilterConfig fConfig) throws ServletException {
    }
 
}
