package com.bulavin.PhoneBook.storage;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class UserStorageTest {

    private UserStorage testStore = new UserStorage();
    private List<PhoneBookRecord> phoneBook = new ArrayList<>();


    User user1;
    User user2;
    User user3;

    User userP1;
    User userP2;
    User userP3;

    PhoneBookRecord record1;
    PhoneBookRecord record2;
    PhoneBookRecord record3;



    @Before
    public void setUp(){

        user1 = new User("Lena", "Ivanova");
        user2 = new User("Vova", "Makarov");
        user3 = new User("Volfgan", "Kurt");

        userP1 = new User("Lena", "Ivanova", phoneBook);
        userP2 = new User("Vova", "Makarov", phoneBook);
        userP3 = new User("Volfgan", "Kurt", phoneBook);

        record1 = new PhoneBookRecord("Petya", "111111");
        record2 = new PhoneBookRecord("Misha", "222222");
        record3 = new PhoneBookRecord("Dasha", "333333");

        phoneBook.add(record1);
        phoneBook.add(record2);
        phoneBook.add(record3);
    }

    @After
    public void cleanUp(){
        UserStorage.getStore().clear();
        User.setIdCounter(0);
        PhoneBookRecord.setIdCounter(0L);
    }

//    @Test
//    public void createUser() {
//
//        testStore.createUser("Lena", "Ivanova");
//
//        Assert.assertEquals(user1, UserStorage.getStore().get(7L));
//
//    }
//
//    @Test
//    public void testCreateUser() {
//
//
//        testStore.createUser("Lena", "Ivanova", phoneBook);
//
//        Assert.assertEquals(userP1, UserStorage.getStore().get(7L));
//    }
//
//    @Test
//    public void deleteUser() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        testStore.deleteUser(1);
//
//        Assert.assertNull(UserStorage.getStore().get(1L));
//    }
//
//    @Test
//    public void getUserById() {
//
//        UserStorage.getStore().put(1L, userP1);
//        User actualUser = testStore.getUserById(1L);
//
//        Assert.assertEquals(userP1, actualUser);
//    }
//
//    @Test
//    public void getAllUser() {
//
//        UserStorage.getStore().put(1L, userP1);
//        UserStorage.getStore().put(2L, userP2);
//
//        List<User> actual = new ArrayList<>(testStore.getAllUser().values());
//
//        List<User> expected = new ArrayList<>();
//        expected.add(userP1);
//        expected.add(userP2);
//
//        Assert.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void pathUser() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        testStore.pathUser(1, "Masha", "Smirnova");
//
//        User actual = new User ("Masha","Smirnova", phoneBook );
//
//        Assert.assertEquals(actual, UserStorage.getStore().get(1L));
//
//    }
//
//    @Test
//    public void searchUser() {
//
//        UserStorage.getStore().put(1L, userP1);
//        UserStorage.getStore().put(2L, userP2);
//        UserStorage.getStore().put(3L, userP3);
//
//        List<User> actual = new ArrayList<>(testStore.searchUser("Vo"));
//        List<User> expected = new ArrayList<>();
//        expected.add(userP2);
//        expected.add(userP3);
//
//        Assert.assertEquals(actual, expected);
//    }
//
//
//
//    @Test
//    public void createRecord() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        testStore.createRecord(1, "Sveta", "444444");
//        PhoneBookRecord expected = new PhoneBookRecord("Sveta", "444444");
//        expected.setRecordId(4L);
//
//        Assert.assertEquals(UserStorage.getStore().get(1L).getPhoneBook().get(3), expected);
//    }
//
//    @Test
//    public void getRecordById() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        PhoneBookRecord expected = testStore.getRecordById(1, 2);
//        PhoneBookRecord actual = new PhoneBookRecord("Misha","222222");
//        actual.setRecordId(2L);
//
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test
//    public void deleteRecord() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        testStore.deleteRecord(1,1);
//
//        List<PhoneBookRecord> expected = new ArrayList<>();
//        expected.add(new PhoneBookRecord("Misha","222222"));
//        expected.get(0).setRecordId(2L);
//        expected.add(new PhoneBookRecord("Dasha", "333333"));
//        expected.get(1).setRecordId(3L);
//
//        Assert.assertEquals(UserStorage.getStore().get(1L).getPhoneBook(), expected);
//    }
//
//    @Test
//    public void pathRecord() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        testStore.pathRecord(1, 1, "Sveta", "444444");
//
//        PhoneBookRecord expected = new PhoneBookRecord("Sveta", "444444");
//        expected.setRecordId(1L);
//
//        Assert.assertEquals(UserStorage.getStore().get(1L).getPhoneBook().get(0), expected);
//    }
//
//    @Test
//    public void getAllRecordsUser() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        List<PhoneBookRecord> actual = new ArrayList<>(testStore.getAllRecordsUser(1));
//        List<PhoneBookRecord> expected = new ArrayList<>();
//        expected.add(new PhoneBookRecord("Petya", "111111"));
//        expected.get(0).setRecordId(1L);
//        expected.add(new PhoneBookRecord("Misha", "222222"));
//        expected.get(1).setRecordId(2L);
//        expected.add(new PhoneBookRecord("Dasha", "333333"));
//        expected.get(2).setRecordId(3L);
//
//        Assert.assertEquals(actual, expected);
//
//    }
//
//    @Test
//    public void searchRecord() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        List<PhoneBookRecord> actual = new ArrayList<>(testStore.searchRecord(1, "222222"));
//        List<PhoneBookRecord> expected = new ArrayList<>();
//        expected.add(0, new PhoneBookRecord ("Misha", "222222"));
//        expected.get(0).setRecordId(2L);
//
//        Assert.assertEquals(expected, actual);
//    }
}