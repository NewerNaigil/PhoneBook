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








//    public static class RestResp {
//        String param1;
//        String param2;
//
//        public String getParam1() {return param1;}
//
//        public void setParam1(String param1) {this.param1 = param1;}
//
//        public String getParam2() {return param2;}
//
//        public void setParam2(String param2) {this.param2 = param2;}
//    }



//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public RestResp restMethod(String name){
//        RestResp result = new RestResp();
//        result.setParam1("1");
//        result.setParam2(name);
//        return result;

    }
