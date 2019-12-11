package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UserStorageTest {

//    @Before
//    public void setUp(){
//        UserStorage testStore = new UserStorage();
//        List<PhoneBookRecord> phoneBook = new ArrayList<>();
//        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
//        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
//        User user = new User("Lena", "Ivanova", phoneBook);
//
//    }


    @Test
    void createUser() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user = new User("Lena", "Ivanova");

        testStore.createUser("Lena", "Ivanova");

        Assert.assertEquals(user, UserStorage.getStore().get(1L));

    }

    @Test
    void testCreateUser() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user = new User("Lena", "Ivanova", phoneBook);

        testStore.createUser("Lena", "Ivanova", phoneBook);

        Assert.assertEquals(user, UserStorage.getStore().get(1L));
    }

    @Test
    void deleteUser() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user);

        testStore.deleteUser(1);

        Assert.assertNull(UserStorage.getStore().get(1L));

    }

    @Test
    void getUserById() {

        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user);
        User actualUser = testStore.getUserById(1L);

        Assert.assertEquals(user, actualUser);
    }

    @Test
    void getAllUser() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);
        User user2 = new User("Vova", "Makarov", phoneBook);

        UserStorage.getStore().put(1L, user1);
        UserStorage.getStore().put(2L, user2);

        List<User> actual = new ArrayList<>(testStore.getAllUser().values());

        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        Assert.assertEquals(expected, actual);

    }

    @Test
    void pathUser() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        testStore.pathUser(1, "Masha", "Smirnova");

        User actual = new User ("Masha","Smirnova", phoneBook );

        Assert.assertEquals(actual, UserStorage.getStore().get(1L));

    }

    @Test
    void searchUser() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);
        User user2 = new User("Vova", "Makarov", phoneBook);
        User user3 = new User("Volfgan", "Kurt", phoneBook);


        UserStorage.getStore().put(1L, user1);
        UserStorage.getStore().put(2L, user2);
        UserStorage.getStore().put(3L, user3);

        List<User> actual = new ArrayList<>(testStore.searchUser("Vo"));
        List<User> expected = new ArrayList<>();
        expected.add(user2);
        expected.add(user3);

        Assert.assertEquals(actual, expected);
    }



    @Test
    void createRecord() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        testStore.createRecord(1, "Dasha", "Makarova");
        PhoneBookRecord expected = new PhoneBookRecord("Dasha", "Makarova");
        expected.setRecordId(1L);

        Assert.assertEquals(UserStorage.getStore().get(1L).getPhoneBook().get(0), expected);
    }

    @Test
    void getRecordById() {

        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        PhoneBookRecord expected = testStore.getRecordById(1, 2);
        PhoneBookRecord actual = new PhoneBookRecord("Misha","222222");
        actual.setRecordId(2L);

        Assert.assertEquals(actual, expected);
    }

    @Test
    void deleteRecord() {

        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        testStore.deleteRecord(1,2);

        List<PhoneBookRecord> expected = new ArrayList<>();
        expected.add(new PhoneBookRecord("Petya", "111111"));
        expected.get(0).setRecordId(1L);

        Assert.assertEquals(UserStorage.getStore().get(1L).getPhoneBook(), expected);
    }

    @Test
    void pathRecord() {

        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        testStore.pathRecord(1, 1, "Dasha", "333333");

        PhoneBookRecord expected = new PhoneBookRecord("Dasha", "333333");
        expected.setRecordId(1L);

        Assert.assertEquals(UserStorage.getStore().get(1L).getPhoneBook().get(0), expected);
    }

    @Test
    void getAllRecordsUser() {

        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        List<PhoneBookRecord> actual = new ArrayList<>(testStore.getAllRecordsUser(1));
        List<PhoneBookRecord> expected = new ArrayList<>();
        expected.add(new PhoneBookRecord("Petya", "111111"));
        expected.get(0).setRecordId(1L);
        expected.add(new PhoneBookRecord("Misha", "222222"));
        expected.get(1).setRecordId(2L);

        Assert.assertEquals(actual, expected);

    }

    @Test
    void searchRecord() {
        UserStorage testStore = new UserStorage();
        List<PhoneBookRecord> phoneBook = new ArrayList<>();
        phoneBook.add(new PhoneBookRecord("Petya", "111111"));
        phoneBook.add(new PhoneBookRecord("Misha", "222222"));
        User user1 = new User("Lena", "Ivanova", phoneBook);

        UserStorage.getStore().put(1L, user1);

        List<PhoneBookRecord> actual = new ArrayList<>(testStore.searchRecord(1, "222222"));
        List<PhoneBookRecord> expected = new ArrayList<>();
        expected.add(0, new PhoneBookRecord ("Misha", "222222"));
        expected.get(0).setRecordId(2L);

        Assert.assertEquals(expected, actual);
    }
}