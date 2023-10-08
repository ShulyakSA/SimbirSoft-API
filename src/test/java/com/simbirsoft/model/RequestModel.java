package com.simbirsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestModel extends EntityModel {
    private Addition addition;
}
