package com.simbirsoft.model;
/**
 * Базовая модель ответов и запросов
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityModel {
    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;
    private String title;
    private Boolean verified;
}
