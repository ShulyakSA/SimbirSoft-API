package com.simbirsoft.model;
/**
 * Модель ответа метода 'GET'. Метод возвращает одну сущность
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetEntityModel extends EntityModel {
    private ResponseGetEntityAddition addition;
    private int id;
}
