package com.example.demo.sercurity;

import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService=new JwtService();

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if(username==null){
            username="";
        }

        if(password==null){
            password="";
        }

        //测试用户名密码
        System.out.println("Login: "+username);
        System.out.println("Login password: "+password);


        username=username.trim();

        ArrayList<GrantedAuthority> authorities=new ArrayList<>();
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password,authorities);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,Authentication authResult){
        UserEntity userEntity=(UserEntity)authResult.getDetails();
        //以下为测试

        System.out.println(userEntity.getId());
        System.out.println(userEntity.getUsername());
        System.out.println(userEntity.getPassword());
        System.out.println(userEntity.getRole());
        System.out.println(userEntity.getName());

        String jwtString = jwtService.generateJwt(userEntity);
        System.out.println("jwtString: "+jwtString);
        int isActive=userEntity.getIs_active();
        String  role;
        if(userEntity.getRole()==1) {
            role="Teacher";
        } else {
            role="Student";
        }
        response.addHeader("token","Bearer "+jwtString);
        response.addHeader("role",role);
        response.addHeader("active",String.valueOf(isActive));

    }
}
