package com.vitale.exercise2b.repository.generic;

import java.util.List;

public interface Repository<T extends Querable> {

    List<T> add(T item);
    void remove(T item);
    void update(T item);

    List<? extends Querable> query(StreamSpecification<T> specification);
}
