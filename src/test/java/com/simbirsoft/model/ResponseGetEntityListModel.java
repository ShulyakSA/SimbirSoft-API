package com.simbirsoft.model;
/**
 * Модель ответа метода 'GETAll' метод возвращает список сущностей
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetEntityListModel extends ResponseGetEntityModel {
    private ArrayList<ResponseGetEntityModel> entity;
}
