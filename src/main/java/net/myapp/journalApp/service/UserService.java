package net.myapp.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.myapp.journalApp.entity.User;
import net.myapp.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// ✅ ADD this
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
   // private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public  boolean saveNewEntry(User user){
        try{
            String hashed = passwordEncoder.encode(user.getPassword());
            System.out.println("Saving password as: " + hashed);  // 👈 add this
            user.setPassword(hashed);
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("Exception while saving user");
            return false;
        }

    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public List<User> getAll(){
        return  userRepository.findAll();
    }
//    optional is a box basically data ho skta ya nhi ho skta
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // ✅ Correct
    }


}
//controller --> service --> repository