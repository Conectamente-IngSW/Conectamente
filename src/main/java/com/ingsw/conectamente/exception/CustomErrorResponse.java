package com.ingsw.conectamente.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CustomErrorResponse {
    private LocalDateTime dateTime;
    private String message;
    private String details;
}
