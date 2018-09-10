package com.vitale.exercise2b.data;

import java.util.function.Predicate;

public class AllSpecification<T> implements StreamSpecification<T> {

    @Override
    public Predicate<T> toPredicate() {
        return x -> true;
    }
}
