package com.cookiepaper.service;

import javax.mail.internet.MimeMessage;

public interface MailService {

    // 메일 생성
    public MimeMessage createMessage(String usEmail, String type) throws Exception;

    // 메일 발송
    public String sendMail(String usEmail, String type) throws Exception;

}
