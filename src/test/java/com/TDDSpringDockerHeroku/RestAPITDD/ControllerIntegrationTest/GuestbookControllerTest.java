package com.TDDSpringDockerHeroku.RestAPITDD.ControllerIntegrationTest;

import com.TDDSpringDockerHeroku.RestAPITDD.Model.Guest;
import com.TDDSpringDockerHeroku.RestAPITDD.Model.GuestBook;
import com.TDDSpringDockerHeroku.RestAPITDD.Repository.GuestBookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GuestbookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private GuestBook guestBook;

    @Autowired
    private GuestBookRepository repository;


    @BeforeEach
    public void setUp(){
        repository.deleteAll();
    }

    @Test
    public void test_createGuestBook() throws Exception {

        guestBook = new GuestBook("wedding");

        mockMvc
                .perform(post("/api/guestbook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(guestBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("wedding"))
                .andReturn();

    }

    @Test
    public void addGuestInGuestBook() throws Exception {

        guestBook = new GuestBook("wedding");

        Guest guest1 = new Guest("bob","jhon","brother");


        guestBook.addGuest(guest1);


        mockMvc
                .perform(post("/api/guestbook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guestBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("wedding"))
                .andExpect(jsonPath("$.guests.[0].firstName").value("bob"))
                .andExpect(jsonPath("$.guests.[0].lastName").value("jhon"))
                .andExpect(jsonPath("$.guests.[0].relationship").value("brother"))
                .andReturn();

    }

    @Test
    public void getAllGuestFromGuestBook() throws Exception {

        guestBook = new GuestBook("wedding");

        Guest guest1 = new Guest("bob","jhon","brother");
        Guest guest2 = new Guest("mark","go","cousin");
        Guest guest3 = new Guest("sci","fi","uncle");

        guestBook.addGuest(guest1);
        guestBook.addGuest(guest2);
        guestBook.addGuest(guest3);



        mockMvc
                .perform(post("/api/guestbook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guestBook)))
                .andExpect(status().isCreated());

        mockMvc
                .perform(get("/api/guestbook/get-guests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("[2].firstName").value("sci"))
                .andExpect(jsonPath("[2].lastName").value("fi"))
                .andExpect(jsonPath("[2].relationship").value("uncle"));

    }





}
