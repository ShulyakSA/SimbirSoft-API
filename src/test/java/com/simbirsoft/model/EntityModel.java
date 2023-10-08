package com.simbirsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityModel {
    @JsonProperty("important_numbers")
    private int[] importantNumbers;
    private String title;
    private boolean verified;
}
