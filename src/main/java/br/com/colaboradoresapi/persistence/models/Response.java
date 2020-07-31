package br.com.colaboradoresapi.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

    private String status;

    private T object;
}
