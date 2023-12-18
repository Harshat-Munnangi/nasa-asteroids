package org.nasa.neo.exception;

public record ErrorDto(
        int code,
        String http_error,
        String error_message,
        String request
) { }
