package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DemoController {


    private UserStorage userStorage = new UserStorage();

    @PostMapping("/create")
    public void createUser(@RequestParam String firstName, @RequestParam String lastName){
        userStorage.createUser(firstName, lastName);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userStorage.createUser(user.getFirstName(), user.getLastName(), user.getPhoneBook());
    }

    @GetMapping("/getAllUser")
    public Map<Long, User> userList(){
        return userStorage.getAllUser();
    }

    @GetMapping("/getUser/{userId}")
    public  User user(@PathVariable long userId){
        return userStorage.getUserById(userId);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUser(@PathVariable long userId){
        userStorage.deleteUser(userId);
    }

    @PatchMapping("/pathUser/{userId}/{firstName}")
    public void pathUser(@PathVariable long userId, @PathVariable String firstName){
        userStorage.pathUser(userId, firstName);
    }

    @PostMapping("/createRecord")
    public void createRecord(@RequestParam long id,
                             @RequestParam String recordName,
                             @RequestParam String recordNumber){
        userStorage.createRecord(id, recordName, recordNumber);
    }

    @GetMapping("/getRecord/{userId}/{recordId}")
    public PhoneBookRecord record(@PathVariable long userId, @PathVariable Integer recordId){
        return userStorage.getRecordById(userId, recordId);
    }

    @DeleteMapping("/deleteRecord/{userId}/{recordId}")
    public void deleteRecord(@PathVariable long userId, @PathVariable Integer recordId){
        userStorage.deleteRecord(userId, recordId);
    }
}