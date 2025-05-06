package ru.degree.shop.service.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.degree.shop.DTO.token.ReceiptPostDto;
import ru.degree.shop.exception.NotFoundException;
import ru.degree.shop.model.Order;
import ru.degree.shop.repository.OrderRepository;
import ru.degree.shop.repository.UserRepository;
import ru.degree.shop.service.EmailService;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public void makeReceiptEmail(String to, String orderNumber, String receiptPath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        Context context = new Context(Locale.getDefault());
        context.setVariable("orderNumber", orderNumber);

        String html = templateEngine.process("receipt-email", context);

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Ваш чек №" + orderNumber);
        mimeMessageHelper.setText(html, true);

        FileSystemResource file = new FileSystemResource(new File(receiptPath));
        mimeMessageHelper.addAttachment("Чек_" + orderNumber + ".png", file);

        mailSender.send(mimeMessage);
    }

    @Override
    @SneakyThrows
    public void sendReceiptEmail(ReceiptPostDto receiptPostDto) {
        Order order = orderRepository.findById(receiptPostDto.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found"));

//        User user = userRepository.findUserByEmail(order.getUser().getEmail())
//                .orElseThrow(() -> new NotFoundException("User not found"));

        String imagePath = saveReceiptImage(receiptPostDto.getReceiptImage());

        this.makeReceiptEmail(
                order.getUser().getEmail(),
                order.getId().toString(),
                imagePath);

        Files.deleteIfExists(Paths.get(imagePath));
    }

    @SneakyThrows
    private String saveReceiptImage(String base64Image) {
        String[] parts = base64Image.split(",");
        byte[] imageBytes = Base64.getDecoder().decode(parts[1]);

        String fileName = "receipt_" + UUID.randomUUID() + ".png";
        Path path = Paths.get("temp", fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, imageBytes);

        return path.toString();
    }
}
