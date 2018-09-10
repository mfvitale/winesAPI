package com.vitale.exercise2b.data;

import java.util.function.Predicate;

public interface StreamSpecification<T> {

    public Predicate<T> toPredicate();
}
