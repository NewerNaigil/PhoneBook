package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class UserStorage {
    private static Map<Long, User> store = new HashMap();
    private static long key = 0;

    public void createUser(String userName){
        ++key;
        store.put(key, new User(userName));
    }

    public void createUser(String userName, List<PhoneBookRecord> records){
        ++key;
        store.put(key, new User(userName, records));
    }

    public void deleteUser (long id){
        store.remove(id);
    }

    public User getUserById(long id){
        return store.get(id);
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        userList.addAll(store.values());
        return userList;
    }

//    public void pathUser(long id){
//        store.put()
//    }
}
