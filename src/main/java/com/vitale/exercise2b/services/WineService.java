package com.vitale.exercise2b.services;

import com.vitale.exercise2b.data.AllSpecification;
import com.vitale.exercise2b.data.Wine;
import com.vitale.exercise2b.data.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {

    @Autowired
    private WineRepository wineRepository;

    public List<Wine> getCatalog() {

        AllSpecification<Wine> allWine =  new AllSpecification<Wine>();

        return wineRepository.query(allWine);
    }

    public List<Wine> addToCatalog(Wine wine) {

        return wineRepository.add(wine);
    }
}
