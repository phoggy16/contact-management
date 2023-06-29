package com.cms.cms.exceptionhandling;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public class CustomError {

        private final HttpStatus status;
        private final String message;
        private final Instant timestamp;

        public CustomError(HttpStatus status, String message, Instant timestamp) {
            this.status= status;
            this.message = message;
            this.timestamp = timestamp;
        }

        public HttpStatus getStatus() {
            return this.status;
        }

        public String getMessage() {
            return this.message;
        }

        public Instant getTimestamp() {
            return this.timestamp;
        }
    }
