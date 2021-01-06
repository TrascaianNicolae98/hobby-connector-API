package com.api.service;

import com.api.entities.AppUser;
import com.api.entities.ConfirmationToken;
import com.api.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  private JavaMailSender javaMailSender;
  @Autowired ConfirmationTokenRepository confirmationTokenRepository;

  @Autowired
  public EmailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Async
  public void sendEmail(SimpleMailMessage email) {
    javaMailSender.send(email);
  }

  public void verifyConfirmationToken(AppUser appUser) {
    ConfirmationToken confirmationToken = new ConfirmationToken(appUser);
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(appUser.getEmail());
    mailMessage.setSubject("Complete Registration!");
    mailMessage.setFrom("wildcats.project98@gmail.com");
    mailMessage.setText(
        "To confirm your account, please click here : "
            + "http://localhost:8080/api/confirm-account?token="
            + confirmationToken.getConfirmationToken());
    this.sendEmail(mailMessage);
    this.confirmationTokenRepository.save(confirmationToken);
  }
}
