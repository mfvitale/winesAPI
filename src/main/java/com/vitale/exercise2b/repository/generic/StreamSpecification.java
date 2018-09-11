package com.vitale.exercise2b.repository.generic;

import java.util.function.Predicate;

/**
 * Specification for Collection query
 * @param <T> a model that extends @{@link Querable}
 */
public interface StreamSpecification<T extends Querable> {

    public Predicate<? extends Querable> toPredicate();
}
