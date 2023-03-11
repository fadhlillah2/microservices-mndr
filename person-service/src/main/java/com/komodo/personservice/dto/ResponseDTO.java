package com.komodo.personservice.dto;


import lombok.*;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResponseDTO<T> {
    public HttpStatus httpStatus;
    public  String message;
    private T data;
}
