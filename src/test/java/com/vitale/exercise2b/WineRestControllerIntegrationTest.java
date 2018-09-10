package com.vitale.exercise2b;

import com.vitale.exercise2b.controllers.WineController;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WineController.class)
public class WineRestControllerIntegrationTest   {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WineService service;

    @Test
    public void passing_name_will_return_hello_name()
            throws Exception {

        when(service.greet("mario")).thenReturn("hello mario");

        mvc.perform(MockMvcRequestBuilders.get("/hello/mario")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("hello mario")));
    }
}
