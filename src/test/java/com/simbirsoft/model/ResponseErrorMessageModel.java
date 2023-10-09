package com.simbirsoft.model;
/**
 * Модель сообщения об ошибке в ответе метода 'GET'. Выводится если сущность не найдена
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseErrorMessageModel {
    private String error;
}
