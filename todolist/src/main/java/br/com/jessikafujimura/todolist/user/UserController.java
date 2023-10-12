package br.com.jessikafujimura.todolist.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
        Optional<UserModel> user = this.userRepository.findByUserName(userModel.getUserName());
        if(user.isPresent()){
            throw new RuntimeException("Deu ruim");
        }
        String passwordCript = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordCript);
         var userPersisted = this.userRepository.save(userModel);
        return new ResponseEntity<UserModel>(userPersisted, null, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserModel> getAllUsers(){
        return this.userRepository.findAll();
    }
    
}
