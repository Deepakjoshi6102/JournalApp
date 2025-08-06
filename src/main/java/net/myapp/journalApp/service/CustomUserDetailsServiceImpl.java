package net.myapp.journalApp.service;

import net.myapp.journalApp.entity.User;
import net.myapp.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println("Trying to load user: " + username);

        User user = userRepository.findByUsername(username);
        if(user!=null){
            System.out.println("Password matches: " + new BCryptPasswordEncoder().matches("ram", user.getPassword()));
            UserDetails userDetails=org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
           return userDetails;
        }
        throw new UsernameNotFoundException("User not Found with this username: " + username);
    }
}
