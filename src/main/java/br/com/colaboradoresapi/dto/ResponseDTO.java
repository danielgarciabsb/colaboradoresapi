package br.com.colaboradoresapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDTO<T> {

    private String status;

    private T data;
}
