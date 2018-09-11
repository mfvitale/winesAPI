package com.vitale.exercise2b.controllers;

import com.vitale.exercise2b.data.Wine;
import com.vitale.exercise2b.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class WineController {

    @Autowired
    private WineService wineService;

    @RequestMapping(path = "/catalog", method = GET)
    public List<Wine> wines(){

        return wineService.getCatalog();
    }

    @RequestMapping(path = "/catalog", method = POST)
    public List<Wine> addWine(@RequestBody Wine wine){

        return wineService.addToCatalog(wine);
    }

    @RequestMapping(path = "/catalog/{wineId}", method = DELETE)
    public List<Wine> removeWine(@PathVariable(name = "wineId") String id) {

        return wineService.removeWine(id);
    }
}
