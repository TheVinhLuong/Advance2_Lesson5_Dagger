package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.response;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class ErrorResponse {
    public ErrorResponse(String message) {
        this.message = message;
    }
    private String message;
    public String getMessage() {
        return message;
    }
}
