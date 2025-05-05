package com.example.api_service_medical.exception;

public class ReportServiceException extends RuntimeException {
    public ReportServiceException(String message) {
        super(message);
    }

    public ReportServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}