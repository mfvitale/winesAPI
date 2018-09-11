package com.vitale.exercise2b.repository.generic;

import java.util.function.Predicate;

public class AllSpecification<T extends Querable> implements StreamSpecification<T> {

    @Override
    public Predicate<T> toPredicate() {
        return x -> true;
    }
}
