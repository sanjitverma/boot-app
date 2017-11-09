package com.example.verma.bootapp.controller;

import com.example.verma.bootapp.BootAppApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by SANJIT on 06/11/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootAppApplication.class)
@WebAppConfiguration
public class ReadingListControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @Before
    public void setupMockMVC(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    public void testgetReadingList() throws Exception {
        mockMvc.perform(get("/sanjit"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("books"))
        .andExpect(model().attribute("books",is(empty())))
        .andExpect(view().name("readingList"));
    }

    @Test
    public void testaddBooktoReadingList(){
        
    }
}