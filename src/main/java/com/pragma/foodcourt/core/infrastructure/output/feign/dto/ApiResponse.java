package com.pragma.foodcourt.core.infrastructure.output.feign.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private boolean ok;
    private String message;
    private T body;
}
