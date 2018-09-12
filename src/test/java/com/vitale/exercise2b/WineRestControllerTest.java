package com.vitale.exercise2b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitale.exercise2b.model.Wine;
import com.vitale.exercise2b.services.WineService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(
        classes = Exercise2bApplication.class)
public class WineRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WineService service;

    private List<Wine> wines = Arrays.asList(
            new Wine("1", "Barolo", "red"),
            new Wine("2", "Falanghina", "white"));

    private ObjectMapper mapper = new ObjectMapper();

    @After
    public void cleanUp() throws Exception {

        String result = mvc.perform(get("/catalog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Wine> wines = mapper.readValue(result,
                mapper.getTypeFactory().constructCollectionType(List.class, Wine.class));

        for (Wine wine : wines) {

            mvc.perform(delete("/catalog/" + wine.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void add_new_wine() throws Exception {

        String wine = mapper.writeValueAsString(wines.get(0));

        mvc.perform(post("/catalog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(wine))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));


        mvc.perform(get("/catalog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void remove_wine() throws Exception {

        String wine = mapper.writeValueAsString(wines.get(0));

        mvc.perform(post("/catalog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(wine))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(delete("/catalog/" + wines.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/catalog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }
}
