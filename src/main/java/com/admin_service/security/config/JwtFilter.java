package com.admin_service.security.config;


import com.admin_service.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;



    @Value("${his.client.id}")
    private String hisClientId;
    @Value("${patient.client.id}")
    private String patientClientId;
    @Value("${cms.client.id}")
    private String cmsClientId;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth=request.getHeader("Authorization");
        if(auth!=null && !"".equals(auth) && auth.startsWith("Bearer ")){
            String subject=jwtService.extractID(auth);
            if(patientClientId.equals(subject) && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken ut=new UsernamePasswordAuthenticationToken(patientClientId,null,null);
                ut.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(ut);
            }
            else if(hisClientId.equals(subject) && SecurityContextHolder.getContext().getAuthentication()==null){

                UsernamePasswordAuthenticationToken ut=new UsernamePasswordAuthenticationToken(hisClientId,null,null);
                ut.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(ut);
                System.out.println("<---- HIS authentication ---->");
            }
            else if(cmsClientId.equals(subject) && SecurityContextHolder.getContext().getAuthentication()==null){

                UsernamePasswordAuthenticationToken ut=new UsernamePasswordAuthenticationToken(cmsClientId,null,null);
                ut.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(ut);
                System.out.println("<---- CMS authentication ---->");
            }
        }
        filterChain.doFilter(request,response);
    }
}