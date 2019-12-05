package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    private UserStorage userStorage = new UserStorage();

    @PostMapping("/create")
    public void createUser (@RequestBody String userName){
        userStorage.createUser(userName);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody String userName, List<PhoneBookRecord> records){
        userStorage.createUser(userName, records);
    }

    @GetMapping("/getAllUser")
    public List<User> userList(){
        return userStorage.getAllUser();
    }


    }
