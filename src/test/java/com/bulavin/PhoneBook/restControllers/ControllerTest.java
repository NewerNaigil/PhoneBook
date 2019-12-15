package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ControllerTest {

    @Autowired
    UserStorage testStore = new UserStorage();
    List<PhoneBookRecord> phoneBook;

    User userP1;
    User userP2;
    User userP3;

    PhoneBookRecord record1;
    PhoneBookRecord record2;
    PhoneBookRecord record3;

    @Autowired
    private Controller controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp(){

        phoneBook = new ArrayList<>();

        record1 = new PhoneBookRecord("Petya", "111111");
        record2 = new PhoneBookRecord("Misha", "222222");
        record3 = new PhoneBookRecord("Dasha", "333333");

        phoneBook.add(record1);
        phoneBook.add(record2);
        phoneBook.add(record3);

        userP1 = new User("Lena", "Ivanova", phoneBook);
        userP2 = new User("Vova", "Makarov", phoneBook);
        userP3 = new User("Volfgan", "Kurt", phoneBook);

    }

    @Test
    public void test(){

    }

    @Test
    public void createUser() throws Exception {
        this.mockMvc.perform(post("/create").param("firstName", "Lena")
                .param("lastName", "Makarova"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("Vova", "Makarov", phoneBook);
        this.mockMvc.perform(post("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь создан. Имя: Vova"));

    }

    @Test
    public void getAllUser() throws Exception {

        UserStorage.getStore().put(1L, userP1);
        UserStorage.getStore().put(2L, userP2);

        this.mockMvc.perform(get("/getAllUser").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"1\":" +
                                "{\"firstName\":\"Lena\"," +
                                "\"lastName\":\"Ivanova\"," +
                                "\"phoneBook\":[" +
                                        "{\"recordId\":1," +
                                            "\"recordName\":\"Petya\"," +
                                            "\"recordNumber\":\"111111\"}," +
                                        "{\"recordId\":2," +
                                            "\"recordName\":\"Misha\"," +
                                            "\"recordNumber\":\"222222\"}," +
                                        "{\"recordId\":3," +
                                            "\"recordName\":\"Dasha\"," +
                                            "\"recordNumber\":\"333333\"}]}," +
                                "\"2\":" +
                                "{\"firstName\":\"Vova\"," +
                                "\"lastName\":\"Makarov\"," +
                                "\"phoneBook\":[" +
                                        "{\"recordId\":1," +
                                            "\"recordName\":\"Petya\"," +
                                            "\"recordNumber\":\"111111\"}," +
                                        "{\"recordId\":2," +
                                            "\"recordName\":\"Misha\"," +
                                            "\"recordNumber\":\"222222\"}," +
                                        "{\"recordId\":3," +
                                            "\"recordName\":\"Dasha\"," +
                                            "\"recordNumber\":\"333333\"}]}}"));
    }

//    @Test
//    public void getUserById() {
//
//        UserStorage.getStore().put(1L, userP1);
//
//        this.mockMvc.perform(get("/getUserById").param())
//
//    }
//
//    @Test
//    public void deleteUser() {
//    }
//
//    @Test
//    public void pathUser() {
//    }
//
//    @Test
//    public void searchUser() {
//    }
//
//    @Test
//    public void createRecord() {
//    }
//
//    @Test
//    public void getRecordById() {
//    }
//
//    @Test
//    public void deleteRecord() {
//    }
//
//    @Test
//    public void patchRecord() {
//    }
//
//    @Test
//    public void getAllRecordsUser() {
//    }
//
//    @Test
//    public void searchRecord() {
//    }
}