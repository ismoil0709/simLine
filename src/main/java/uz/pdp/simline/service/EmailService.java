package uz.pdp.simline.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import uz.pdp.simline.dto.request.UserRegisterDto;
import uz.pdp.simline.entity.User;

import java.io.IOException;

@Service
public interface EmailService {
    void sendEmailVerificationMessage(UserRegisterDto user);
}
