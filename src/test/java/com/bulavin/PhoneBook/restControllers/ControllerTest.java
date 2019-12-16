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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

//    JSONArray jsonArray = new JSONArray();

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

//        JSONObject ert =  new JSONObject();
//        ert.put()


    }
//          Корректировать!!!!
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
//          Выяснить!!!!
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

    @Test
    public void getUserById() throws Exception{

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(get("/getUser/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userID\":1,\"firstName\":\"Lena\",\"lastName\":\"Ivanova\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]}"));
    }

    @Test
    public void deleteUser() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(delete("/deleteUser/").param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь удалён"));

        this.mockMvc.perform(delete("/deleteUser/").param("userId", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь с ID: 2 не существует"));

    }

    @Test
    public void pathUser() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(patch("/pathUser")
                .param("userId", "1")
                .param("firstName", "Vika")
                .param("lastName", "Simon"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь с ID: 1 изменён."));

        this.mockMvc.perform(patch("/pathUser")
                .param("userId", "2")
                .param("firstName", "Vika")
                .param("lastName", "Simon"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь с ID: 2 не существует."));
    }

    @Test
    public void searchUser() throws Exception{

        UserStorage.getStore().put(1L, userP1);
        UserStorage.getStore().put(2L, userP2);
        UserStorage.getStore().put(3L, userP3);

        this.mockMvc.perform(get("/searchUser/vo").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"userID\":2,\"firstName\":\"Vova\",\"lastName\":\"Makarov\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]},{\"userID\":3,\"firstName\":\"Volfgan\",\"lastName\":\"Kurt\",\"phoneBook\":[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]}]"));

    }

    @Test
    public void createRecord() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(post("/createRecord")
                .param("userId", "1")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Телефонная запись добавлена"));

        this.mockMvc.perform(post("/createRecord")
                .param("userId", "2")
                .param("recordName", "Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь с ID: 2 не существует."));
    }

    @Test
    public void getRecordById() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(get("/getRecord/1/2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"}"));
    }

    @Test
    public void deleteRecord() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(delete("/deleteRecord")
                .param("userId", "1")
                .param("recordId", "2"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void patchRecord() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(patch("/patchRecord")
                .param("userId", "1")
                .param("recordId", "2")
                .param("recordName","Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Телефонная запись обновлена"));

        this.mockMvc.perform(patch("/patchRecord")
                .param("userId", "2")
                .param("recordId", "2")
                .param("recordName","Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Пользователь с ID: 2 не существует."));

        this.mockMvc.perform(patch("/patchRecord")
                .param("userId", "1")
                .param("recordId", "4")
                .param("recordName","Lilu")
                .param("recordNumber", "332211"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Телефонная записи с ID: 4 не существует."));
    }

    @Test
    public void getAllRecordsUser() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(get("/getAllRecordsUser/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"recordId\":1,\"recordName\":\"Petya\",\"recordNumber\":\"111111\"},{\"recordId\":2,\"recordName\":\"Misha\",\"recordNumber\":\"222222\"},{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]"));
    }

    @Test
    public void searchRecord() throws Exception {

        UserStorage.getStore().put(1L, userP1);

        this.mockMvc.perform(get("/searchRecord/1/333333").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"recordId\":3,\"recordName\":\"Dasha\",\"recordNumber\":\"333333\"}]"));
    }
}