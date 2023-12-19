package com.niit.todo.usertodoservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilter {
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // Parse and validate the token and set the user id from claims in the request header as an attribute.
        final String authHeader = request.getHeader("Authorization");
        if (request.getMethod().equals("OPTIONS")) {
            //if the method is options the request can pass through not validation of token is required
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or Invalid Token");
        }
        //extract token from the header
        String token = authHeader.substring(7);//Bearer => 6+1 = 7, since token begins with Bearer
        //extract the claims
        Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
        // set the claims in the request attribute and pass it to the next handler
        request.setAttribute("claims", claims);
        //pass the claims in the request, anyone wanting to use it
        filterChain.doFilter(request, response);
    }
}

