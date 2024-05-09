package xchange.x_change.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import xchange.x_change.util.JwtUtil;


import java.io.IOException;

public class JwtRequestFilter extends UsernamePasswordAuthenticationFilter {
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Implementación de la lógica para manejar el JWT aquí
        chain.doFilter(request, response);
    }
}
