package com.vitale.exercise2b.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WarehouseService {

    private static final Integer DEFAULT_QUANTITY = 0;

    private ConcurrentHashMap<String, AtomicInteger> wineBottlesStock = new ConcurrentHashMap<>();

    public void increaseQuantity(String wineId, int quantity){

        if( wineBottlesStock.putIfAbsent(wineId, new AtomicInteger(quantity)) != null ) {
            wineBottlesStock.get(wineId).addAndGet(quantity);
        }
    }

    public void decreaseQuantity(String wineId, int quantity) {

        wineBottlesStock.computeIfPresent(wineId, (s, atomicInteger) -> {
            int newQuantity = atomicInteger.get() - quantity;
            return new AtomicInteger(newQuantity);
        });
    }

    public Integer getQuantityById(String itemId) {

        return wineBottlesStock.getOrDefault(itemId, new AtomicInteger(DEFAULT_QUANTITY)).get();
    }
}
