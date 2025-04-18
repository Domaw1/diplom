package ru.degree.shop.service;

import jakarta.mail.MessagingException;
import ru.degree.shop.DTO.token.ReceiptPostDto;

import java.io.IOException;

public interface EmailService {
    void makeReceiptEmail(String to, String orderNumber, String receiptPath) throws MessagingException, IOException;
    void sendReceiptEmail(ReceiptPostDto receiptPostDto);
}
