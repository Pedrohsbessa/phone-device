package com.project._global.application.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
}