package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DemoController {


    private UserStorage userStorage = new UserStorage();

    @PostMapping("/create")
    public void createUser(@RequestParam String firstName,
                           @RequestParam String lastName){
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

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam long userId){
        userStorage.deleteUser(userId);
    }

    @PatchMapping("/pathUser")
    public void pathUser(@RequestParam long userId,
                         @RequestParam String firstName,
                         @RequestParam String lastName){
        userStorage.pathUser(userId, firstName, lastName);
    }

    @GetMapping("/searchUser/{searchRequest}")
    public List<User> searchUser(@PathVariable String searchRequest){
        return userStorage.searchUser(searchRequest);
    }





    @PostMapping("/createRecord")
    public void createRecord(@RequestParam long userId,
                             @RequestParam String recordName,
                             @RequestParam String recordNumber){
        userStorage.createRecord(userId, recordName, recordNumber);
    }

    @GetMapping("/getRecord/{userId}/{recordId}")
    public PhoneBookRecord record(@PathVariable long userId,
                                  @PathVariable long recordId){
        return userStorage.getRecordById(userId, recordId);
    }

    @DeleteMapping("/deleteRecord")
    public void deleteRecord(@RequestParam long userId,
                             @RequestParam long recordId){
        userStorage.deleteRecord(userId, recordId);
    }

    @PatchMapping("/patchRecord")
    public void patchRecord(@RequestParam long userId,
                            @RequestParam long recordId,
                            @RequestParam String recordName,
                            @RequestParam String recordNumber){
        userStorage.pathRecord(userId, recordId, recordName, recordNumber);
    }

    @GetMapping("/getAllRecordsUser/{userId}")
    public List<PhoneBookRecord> allRecordsUser(@PathVariable long userId){
        return userStorage.getAllRecordsUser(userId);
    }

    @GetMapping("/searchRecord/{userId}/{searchRequest}")
    public List<PhoneBookRecord> searchRecord(@PathVariable long userId,
                                              @PathVariable String searchRequest){
        return userStorage.searchRecord(userId, searchRequest);
    }


}