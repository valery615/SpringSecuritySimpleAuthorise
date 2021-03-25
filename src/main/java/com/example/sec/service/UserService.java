package com.example.sec.service;



import com.example.sec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.example.sec.model.User userByEmail = userRepository.getUserByEmail(s);
        if (userByEmail == null){
            throw new UsernameNotFoundException("Email does not found");
        }
        UserDetails user = User.withUsername(userByEmail.getEmail()).password(userByEmail.getPassword()).authorities("USER").build();
        return user;
    }
}
