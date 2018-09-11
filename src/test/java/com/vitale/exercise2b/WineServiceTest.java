package com.vitale.exercise2b;

import com.vitale.exercise2b.model.Wine;
import com.vitale.exercise2b.repository.WineRepository;
import com.vitale.exercise2b.repository.generic.ByIdSpecification;
import com.vitale.exercise2b.services.WineService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WineServiceTest {

    @Autowired
    WineService service;

    @MockBean
    private WineRepository repository;

    private List<Wine> allWines;

    @Before
    public void setUp() {

        allWines = Arrays.asList(
                new Wine("661bc1b0-cea8-4e50-a1ee-6bdc64c9bd3a", "Barolo", "red"),
                new Wine("b74632f6-6eae-4579-bcc1-e3ce3d412dc6", "Falanghina", "white")
        );
    }

    @Test
    public void not_present_element_should_not_call_remove() {

        String idToRemove = "8ac0af5a-8a9d-406e-8749-a5a775c89926";
        ByIdSpecification<Wine> specification = new ByIdSpecification<>(idToRemove);

        given(repository.query(specification)).willReturn(Collections.emptyList());

        when(service.removeWine(idToRemove)).thenReturn(allWines);

        then(repository).should(times(0)).remove(any());
    }

    @Test
    public void present_element_should_call_remove() {

        String idToRemove = "661bc1b0-cea8-4e50-a1ee-6bdc64c9bd3a";
        ByIdSpecification<Wine> specification = new ByIdSpecification<>(idToRemove);

        given(repository.query(specification)).willReturn(Collections.singletonList(allWines.get(0)));

        when(service.removeWine(idToRemove)).thenReturn(Collections.singletonList(allWines.get(1)));

        then(repository).should(times(1)).remove(any());
    }
}
