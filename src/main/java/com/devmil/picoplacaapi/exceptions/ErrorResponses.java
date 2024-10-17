package com.devmil.picoplacaapi.exceptions;

import java.time.LocalDateTime;

public class ErrorResponses {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;

    public ErrorResponses(LocalDateTime timestamp, int status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
