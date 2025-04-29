package ru.degree.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.degree.shop.DTO.token.ReceiptPostDto;
import ru.degree.shop.service.EmailService;

@RestController
@RequestMapping("api/v1/email")
@RequiredArgsConstructor
public class EmailSenderController {
    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody ReceiptPostDto receiptPostDto) {
        emailService.sendReceiptEmail(receiptPostDto);
        return new ResponseEntity<>("Отправлено!", HttpStatus.OK);
    }
}
