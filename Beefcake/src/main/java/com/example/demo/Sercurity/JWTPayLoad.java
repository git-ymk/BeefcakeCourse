package com.example.demo.Sercurity;

import com.example.demo.Entity.UserEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class JWTPayLoad {
    private Long id;
    private String username;
    private int role;
    private int is_active;
    private String name;

    public JWTPayLoad()
    {

    }

    public JWTPayLoad(Long id,String username,int role,int is_active,String name)
    {
        this.id=id;
        this.username=username;
        this.role=role;
        this.is_active=is_active;
        this.name=name;
    }
    public UserEntity toUser()
    {
        UserEntity user=new UserEntity();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setRole(this.role);
        user.setName(this.name);
        user.setIs_active(this.is_active);
        return user;
    }


}
