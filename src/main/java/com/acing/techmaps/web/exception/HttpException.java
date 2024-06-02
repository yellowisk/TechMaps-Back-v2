package com.acing.techmaps.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic HTTP exception. Can have a custom message and identifier.
 * <p>
 * Note: the identifier is supposed to assist the client when dealing
 * with multiple errors that may have the same status code, while the
 * message should be human-readable.
 */
public class HttpException extends RuntimeException {
    @Getter
    private final HttpStatus status;
    private final Instant timestamp;
    private final String identifier;

    public HttpException(HttpStatus status) {
        super(status.getReasonPhrase());
        this.status = status;
        this.timestamp = Instant.now();
        this.identifier = "";
    }

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.timestamp = Instant.now();
        this.identifier = "";
    }

    public HttpException(HttpStatus status, String message, String identifier) {
        super(message);
        this.status = status;
        this.timestamp = Instant.now();
        this.identifier = identifier;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();

        map.put("status_code", Integer.toString(this.status.value()));
        map.put("identifier", this.identifier);
        map.put("status_canonical", this.status.getReasonPhrase());
        map.put("timestamp", DateTimeFormatter.ISO_INSTANT.format(this.timestamp));
        map.put("message", this.getMessage());

        return map;
    }
}