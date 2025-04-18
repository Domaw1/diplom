package ru.degree.shop.exception;

public class EmailIsTaken extends RuntimeException {
    public EmailIsTaken(String message) {
        super(message);
    }
}
