package com.bulavin.PhoneBook.restControllers;

import com.bulavin.PhoneBook.model.PhoneBookRecord;
import com.bulavin.PhoneBook.model.User;
import com.bulavin.PhoneBook.storage.UserStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ControllerTest {

    User wrongUser;

    User user1;
    User user2;
    User user3;

    PhoneBookRecord record1;
    PhoneBookRecord record2;
    PhoneBookRecord record3;

    @Autowired
    private UserStorage testStore;

    @Autowired
    private Controller controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {

        List<PhoneBookRecord> phoneBook = new ArrayList<>();

        record1 = new PhoneBookRecord("Petya", "111111");
        record2 = new PhoneBookRecord("Misha", "222222");
        record3 = new PhoneBookRecord("Dasha", "333333");

        phoneBook.add(record1);
        phoneBook.add(record2);
        phoneBook.add(record3);

        user1 = new User("Lena", "Ivanova", phoneBook);
        user2 = new User("Vova", "Makarov", phoneBook);
        user3 = new User("Volfgan", "Kurt", phoneBook);

        wrongUser = new User(null, "wrong", phoneBook);
    }

    @After
    public void cleanUp(){
        UserStorage.getStore().clear();
        User.setIdCounter(0);
        PhoneBookRecord.setIdCounter(0L);
    }

    @Test
    public void testCreateUser() throws Exception {
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1)))
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongUser)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllUser() throws Exception {

        UserStorage.getStore().put(1L, user1);
        UserStorage.getStore().put(2L, user2);

        this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"1\":{\"userID\":1,\"firstName\":\"Lena\",\"lastName\":\"Ivanova\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]},\"2\":{\"userID\":2,\"firstName\":\"Vova\",\"lastName\":\"Makarov\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]}}"));
    }

    @Test
    public void getUserById() throws Exception {

        this.mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userID\":1,\"firstName\":\"Lena\",\"lastName\":\"Ivanova\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]}"));
    }

    @Test
    public void deleteUser() throws Exception {

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(delete("/users").param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/users").param("userId", "2"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void pathUser() throws Exception {

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(patch("/users")
                .param("userId", "1")
                .param("firstName", "Vika")
                .param("lastName", "Simon"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(patch("/users")
                .param("userId", "2")
                .param("firstName", "Vika")
                .param("lastName", "Simon"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchUser() throws Exception {

        this.mockMvc.perform(get("/users/search/vo").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        UserStorage.getStore().put(1L, user1);
        UserStorage.getStore().put(2L, user2);
        UserStorage.getStore().put(3L, user3);

        this.mockMvc.perform(get("/users/search/vo").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"userID\":2,\"firstName\":\"Vova\",\"lastName\":\"Makarov\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]},{\"userID\":3,\"firstName\":\"Volfgan\",\"lastName\":\"Kurt\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]}]"));

    }

    @Test
    public void createRecord() throws Exception {

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(post("/records")
                .param("userId", "1")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(post("/createRecord")
                .param("userId", "2")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getRecordById() throws Exception {

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(get("/records/1/2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"}"));

        this.mockMvc.perform(get("/records/2/2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        this.mockMvc.perform(get("/records/1/4").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRecord() throws Exception {

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(delete("/records")
                .param("userId", "1")
                .param("recordId", "2"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/records")
                .param("userId", "2")
                .param("recordId", "2"))
                .andDo(print())
                .andExpect(status().isNotFound());

        this.mockMvc.perform(delete("/records")
                .param("userId", "1")
                .param("recordId", "4"))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void patchRecord() throws Exception {

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(patch("/records")
                .param("userId", "1")
                .param("recordId", "2")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(patch("/records")
                .param("userId", "2")
                .param("recordId", "2")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isNotFound());

        this.mockMvc.perform(patch("/records")
                .param("userId", "1")
                .param("recordId", "4")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllRecordsUser() throws Exception {

        this.mockMvc.perform(get("/records/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(get("/records/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]"));
    }

    @Test
    public void searchRecord() throws Exception {

        this.mockMvc.perform(get("/records/search/1/333333").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        UserStorage.getStore().put(1L, user1);

        this.mockMvc.perform(get("/records/search/1/233333").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        this.mockMvc.perform(get("/records/search/1/333333").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]"));
    }
}