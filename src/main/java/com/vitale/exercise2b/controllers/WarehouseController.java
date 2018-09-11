package com.vitale.exercise2b.controllers;

import com.vitale.exercise2b.data.Order;
import com.vitale.exercise2b.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(path = "/warehouse/increase", method = POST)
    public String putInStock(@RequestBody Order order){

        warehouseService.increaseQuantity(order.getItemId(), order.getQuantity());

        return "OK";
    }

    @RequestMapping(path = "/warehouse/decrease", method = POST)
    public String getFromStock(@RequestBody Order order){

        warehouseService.decreaseQuantity(order.getItemId(), order.getQuantity());

        return "OK";
    }

    @RequestMapping(path = "/warehouse/{itemId}", method = GET)
    public Integer getQuantityById(@PathVariable(name = "itemId") String itemId){

        return warehouseService.getQuantityById(itemId);
    }


}
