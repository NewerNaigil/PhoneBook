package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class UserStorage {
    private static Map<Long, User> store = new HashMap();
    private static Long key = 0L;


    public void createUser (String userName, List<PhoneBookRecord> records){
        ++key;
        store.put(key, new User(userName, records));
    }

//    public List<User> getAllUser (){
//        List<User> userList = new ArrayList<>();
//        userList.addAll(store.values());
//        return userList;
//    }
//test

}
