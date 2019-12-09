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
        userStorage.createUser(user.getUserName(), user.getRecords());
    }

    @GetMapping("/getAllUser")
    public List<User> userList(){
        return userStorage.getAllUser();
    }
    }
