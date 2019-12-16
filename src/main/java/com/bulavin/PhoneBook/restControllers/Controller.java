package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private UserStorage userStorage;

//    @PostMapping("/create")
//    public void createUser(@RequestParam String firstName,
//                           @RequestParam String lastName){
//        userStorage.createUser(firstName, lastName);
//    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userStorage.createUser(user.getFirstName(), user.getLastName(), user.getPhoneBook());
    }

    @GetMapping("/users")
    public Map<Long, User> getAllUser(){
        return userStorage.getAllUser();
    }

    @GetMapping("/users/{userId}")
    public  User getUserById(@PathVariable long userId){
        return userStorage.getUserById(userId);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam long userId){
        userStorage.deleteUser(userId);
    }

    @PatchMapping("/users")
    public void pathUser(@RequestParam long userId,
                         @RequestParam String firstName,
                         @RequestParam String lastName){
        userStorage.pathUser(userId, firstName, lastName);
    }

    @GetMapping("/users/search/{searchRequest}")
    public List<User> searchUser(@PathVariable String searchRequest){
        return userStorage.searchUser(searchRequest);
    }





//    @PostMapping("/createRecord")
//    public String createRecord(@RequestParam long userId,
//                             @RequestParam String recordName,
//                             @RequestParam String recordNumber){
//        return userStorage.createRecord(userId, recordName, recordNumber);
//    }
//
//    @GetMapping("/getRecord/{userId}/{recordId}")
//    public PhoneBookRecord getRecordById(@PathVariable long userId,
//                                         @PathVariable long recordId){
//        return userStorage.getRecordById(userId, recordId);
//    }
//
//    @DeleteMapping("/deleteRecord")
//    public void deleteRecord(@RequestParam long userId,
//                             @RequestParam long recordId){
//        userStorage.deleteRecord(userId, recordId);
//    }
//
//    @PatchMapping("/patchRecord")
//    public String patchRecord(@RequestParam long userId,
//                            @RequestParam long recordId,
//                            @RequestParam String recordName,
//                            @RequestParam String recordNumber){
//        return userStorage.pathRecord(userId, recordId, recordName, recordNumber);
//    }
//
//    @GetMapping("/getAllRecordsUser/{userId}")
//    public List<PhoneBookRecord> getAllRecordsUser(@PathVariable long userId){
//        return userStorage.getAllRecordsUser(userId);
//    }
//
//    @GetMapping("/searchRecord/{userId}/{searchRequest}")
//    public List<PhoneBookRecord> searchRecord(@PathVariable long userId,
//                                              @PathVariable String searchRequest){
//        return userStorage.searchRecord(userId, searchRequest);
//    }
//

}