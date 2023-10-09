package com.simbirsoft.model;
/**
 * Модель ответа метода 'GET' блока addition
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetEntityAddition extends Addition {
    private int id;
}