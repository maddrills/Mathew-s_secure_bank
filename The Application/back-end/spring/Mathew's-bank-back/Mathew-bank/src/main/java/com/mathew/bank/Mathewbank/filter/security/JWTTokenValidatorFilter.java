package com.mathew.bank.Mathewbank.filter.security;

import com.mathew.bank.Mathewbank.constents.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //    SecurityConstants
        //    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
        //    public static final String JWT_HEADER = "Authorization";
        //String jwt = request.getHeader(SecurityConstants.JWT_HEADER);
        //below is the COOKIE approach
        String jwt = null;
        for(var cookie : request.getCookies()){
            if(cookie.getName().equals("Authorization")){
                System.out.print("COOKIE");
                System.out.println(cookie.getValue());
                jwt = cookie.getValue();
            }
        }
        if (null != jwt) {
            try {
                //generating the key
                SecretKey key = Keys.hmacShaKeyFor(
                        SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

                //verification of legitimacy
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload();
                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("authorities");

//                System.out.println("JWT name : "+ username);
//                System.out.println("JWT auth "+ authorities);

                //if successful the result will be stored in SecurityContextHolder
                Authentication auth = new UsernamePasswordAuthenticationToken(username, null,
                        //this comes in a string of comas and values
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token received!");
            }

        }
        filterChain.doFilter(request, response);
    }

    //should be executed for all the api except the login api
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return request.getServletPath().equals("/bankUser/login") ||
                request.getServletPath().equals("/employee/employee-login") ||
                //bellow was done to archive this /exposed/**
                request.getServletPath().split("/")[1].equals("exposed");
    }
}
