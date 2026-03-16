package com.s1.proyecto_spring.exception;

import java.time.LocalDateTime;

public record ErrorResponse
        (LocalDateTime timestamp, int status, String message, String errorCode) {
}
