package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {


    private UserStorage userStorage = new UserStorage();

    @PostMapping("/create")
    public void createUser(@RequestParam String userName){
        userStorage.createUser(userName);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userStorage.createUser(user.getUserName(), user.getPhoneBook());
    }

    @GetMapping("/getAllUser")
    public List<User> userList(){
        return userStorage.getAllUser();
    }

    @GetMapping("/getUser/{id}")
    public  User user(@PathVariable long id){
        return userStorage.getUserById(id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable long id){
        userStorage.deleteUser(id);
    }

    @PatchMapping("/pathUser/{id}/{userName}")
    public void pathUser(@PathVariable long id, @PathVariable String userName){
        userStorage.pathUser(id, userName);
    }


}