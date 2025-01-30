package com.example.MyRecipe.service;

import com.example.MyRecipe.model.MyUser;
import com.example.MyRecipe.repository.MyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {


    private MyUserRepository myUserRepository;


    public MyUserDetailService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUserRepository.findByUsername(username);
        if(myUser.isPresent()) {
            return User.builder()
                    .username(myUser.get().getUsername())
                    .password(myUser.get().getPassword())
                    .roles(getRoles(myUser.get()))
                    .build();
        }
        throw new UsernameNotFoundException(username);
    }


    private String[] getRoles(MyUser user){
        if(user.getRole()==null){
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }



}
