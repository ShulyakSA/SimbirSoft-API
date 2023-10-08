package com.simbirsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModel extends EntityModel {
    private ResponseAddition addition;
    private int id;
}
