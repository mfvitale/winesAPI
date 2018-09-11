package com.vitale.exercise2b.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vitale.exercise2b.repository.generic.Querable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wine extends Querable {

    @JsonProperty("id")
    private String id = UUID.randomUUID().toString();

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @Override
    protected String getIdentifier() {
        return id;
    }
}
