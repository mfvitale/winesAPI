package com.vitale.exercise2b.repository;

import com.vitale.exercise2b.model.Wine;
import com.vitale.exercise2b.repository.generic.Repository;
import com.vitale.exercise2b.repository.generic.StreamSpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class WineRepository implements Repository<Wine> {

    private List<Wine> catalog = new ArrayList<>();

    @Override
    public List<Wine> add(Wine item) {

        catalog.add(item);

        return  catalog;
    }

    @Override
    public void remove(Wine item) {

        catalog.remove(item);
    }

    @Override
    public void update(Wine item) {
        if (catalog.remove(item) ) {
            catalog.add(item);
        }
    }

    @Override
    public List<Wine> query(StreamSpecification<Wine> specification) {

        return catalog.stream()
                .filter((Predicate<? super Wine>) specification.toPredicate())
                .collect(Collectors.toList());
    }
}
