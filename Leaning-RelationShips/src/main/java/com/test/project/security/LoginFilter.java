package com.test.project.security;

import com.test.project.domain.User;
import com.test.project.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Valentin
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private final static int UNAUTHORIZED_CODE = 401;
    @Autowired
    private MainService service;
    private String loginPageUrl;
    public LoginFilter() {
        super("");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        SecurityContextHolder.clearContext();
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        if(login == null || password == null){
            httpServletResponse.setStatus(UNAUTHORIZED_CODE);
            return null;
        }

        User user = service.getUser(login);
        if(user == null){
            httpServletResponse.setStatus(UNAUTHORIZED_CODE);
            return null;
        }
        if(!user.getPassword().equals(password)){
            httpServletResponse.setStatus(UNAUTHORIZED_CODE);
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority(user.getRole())}));
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
    }
}
