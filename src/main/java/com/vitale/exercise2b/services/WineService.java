package com.vitale.exercise2b.services;

import com.vitale.exercise2b.repository.generic.AllSpecification;
import com.vitale.exercise2b.repository.generic.ByIdSpecification;
import com.vitale.exercise2b.model.Wine;
import com.vitale.exercise2b.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {

    @Autowired
    private WineRepository wineRepository;

    public List<Wine> getCatalog() {

        AllSpecification<Wine> allWine =  new AllSpecification<>();

        return wineRepository.query(allWine);
    }

    public List<Wine> addToCatalog(Wine wine) {

        return wineRepository.add(wine);
    }

    public List<Wine> removeWine(String id) {

        List<Wine> wineList = wineRepository.query(new ByIdSpecification<>(id));

        if(!wineList.isEmpty()) {

            wineRepository.remove(wineList.get(0));
        }

        return wineRepository.query(new AllSpecification<>());
    }
}
