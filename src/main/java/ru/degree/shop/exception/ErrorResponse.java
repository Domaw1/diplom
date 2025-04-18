package ru.degree.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int code;
    private Date timestamp;
}
