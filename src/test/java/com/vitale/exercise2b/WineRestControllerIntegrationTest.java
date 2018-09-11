package com.vitale.exercise2b;

import com.vitale.exercise2b.controllers.WineController;
import com.vitale.exercise2b.model.Wine;
import com.vitale.exercise2b.services.WineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WineController.class)
public class WineRestControllerIntegrationTest   {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WineService service;

    @Test
    public void get_wines_list()
            throws Exception {

        List<Wine>  wines = Arrays.asList(new Wine("1", "Barolo", "red"), new Wine("2", "Falanghina", "white"));

        when(service.getCatalog()).thenReturn(wines);

        mvc.perform(MockMvcRequestBuilders.get("/catalog")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Barolo")))
                .andExpect(jsonPath("$[0].type", is("red")))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].name", is("Falanghina")))
                .andExpect(jsonPath("$[1].type", is("white")));
    }
}
