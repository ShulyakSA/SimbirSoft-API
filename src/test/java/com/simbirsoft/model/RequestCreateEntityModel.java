package com.simbirsoft.model;
/**
 * Модель запроса метода 'POST'. Метод создает сущность
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCreateEntityModel extends EntityModel {
    private Addition addition;
}
