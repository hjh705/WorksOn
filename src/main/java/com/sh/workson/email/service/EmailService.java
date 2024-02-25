package com.sh.workson.email.service;

import com.sh.workson.employee.dto.EmployeeCreateDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private EmployeeCreateDto employeeCreateDto;

    public void send(String to, String subject, String body ){

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(to); // 메일 수신자
            mimeMessageHelper.setSubject("환영합니다, " + subject + "님!");// 메일제목

            String htmlBody = "<html><body>"
                    + "<h1>환영합니다, " + subject + "님!</h1>"
                    + "<p>회원가입을 축하드립니다. 아래 링크를 클릭하여 홈페이지로 이동하세요.</p>"
                    + "<p><a href=\"http://localhost:8080/WorksOn/auth/login.do\">홈페이지로 이동</a></p>"
                    + "<p>가입한 아이디: " + to + "</p>"
                    + "<p>임시 비밀번호: " + body + "</p>"
                    + "</body></html>";
            mimeMessageHelper.setText(htmlBody , true); //메일 본분 내용

            mailSender.send(mimeMessage); // 메일발송

        }catch (MessagingException e){
            throw new RuntimeException(e);

        }
    }

}
