package org.example.Model;

public class Result {
    private final String message;
    private final boolean isSuccessful;

    public Result(String message, boolean isSuccessful) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    @Override
    public String toString() {
        return message;
    }
}