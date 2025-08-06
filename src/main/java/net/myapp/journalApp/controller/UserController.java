package net.myapp.journalApp.controller;


import net.myapp.journalApp.entity.User;
import net.myapp.journalApp.repository.UserRepository;
import net.myapp.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//Method inside a controller should be public so that they can be accessed and invoked by spring framework or external http request

@RestController
@RequestMapping("/user")
public class UserController {


      @Autowired
      private UserService userService;

      @Autowired
      private UserRepository userRepository;


//      @GetMapping
//      public  List<User> getAllUsers(){
//          return userService.getAll();
//      }


      @PutMapping
      public ResponseEntity<?> updateUser(@RequestBody User user ){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String name = authentication.getName();

          User userInDb = userService.findByUsername(name);

          if(userInDb!=null){
              userInDb.setUsername(user.getUsername());
              userInDb.setPassword(user.getPassword());
              userService.saveNewEntry(userInDb);
          }
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      @DeleteMapping
      public ResponseEntity<?> deleteUserById(){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String name = authentication.getName();
          userRepository.deleteByUsername(name);

          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }



}
