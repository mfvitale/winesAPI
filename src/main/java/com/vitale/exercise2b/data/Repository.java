package com.vitale.exercise2b.data;

import java.util.List;

public interface Repository<T> {

    List<T> add(T item);
    void remove(T item);
    void update(T item);

    List<T> query(StreamSpecification<T> specification);
}
