package com.vitale.exercise2b;

import com.vitale.exercise2b.repository.WineRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WineRepositoryTest {

    @Autowired
    WineRepository wineRepository;

    public void query_by_id_should_return_the_correct_item() {

        //assert wineRepository.query()
    }

}
