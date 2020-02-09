package ru.quick.approval.system.dbcontroller.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;
import ru.quick.approval.system.dbcontroller.security.tokenModel.ValidatedToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtRequestFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider;
    private RestTemplate restTemplate = new RestTemplate();

    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        if (token != null) {
            try {
                ValidatedToken validatedToken = restTemplate.getForObject("http://localhost:8084/validate_token?token="
                        + token, ValidatedToken.class);
                if (validatedToken != null && validatedToken.isValidated()) {
                    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else if (validatedToken != null && !validatedToken.isValidated()) {
                    throw new BadCredentialsException("token expired " + validatedToken.getMessage());
                }
            } catch (RestClientException e) {
                e.printStackTrace();
                servletRequest.setAttribute("err", e);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
