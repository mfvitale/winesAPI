package com.vitale.exercise2b.repository.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByIdSpecification<T extends Querable> implements StreamSpecification<T> {

    private String identifier;

    @Override
    public Predicate<? extends Querable> toPredicate() {
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return t.getIdentifier().equals(identifier);
            }
        };
    }
}
