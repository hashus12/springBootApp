package com.hasan.springbootapp.controllers;

import com.hasan.springbootapp.entities.User;
import com.hasan.springbootapp.repos.UserRepository;
import com.hasan.springbootapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

//    Post isteği geldiğinde bu metod çağrılır. Gelen istekteki (requestteki) bilgileri alıp bir User objesine maple
//    ve bana o User objesini dön sonra bu objeyi database'e save ediyoruz. jpa repostiryinin save metodu db ye kaydettiği
//    userı geri dönüyor.
    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //custom exception
        return userService.getOneUserById(userId);
    }
//    Burda PostMapping yapamayız /users/id ile. bizim db mize id vererek birinin kaydolmasını istemeyiz normalde id creationı
//    biz handle ediyoruz. Bunu yerine PutMapping kullanıyoruz. Yani var olan idli bir userı değiştirebiliriz.onda değişiklik yapabiliriz
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId ,@RequestBody User newUser){
       return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
