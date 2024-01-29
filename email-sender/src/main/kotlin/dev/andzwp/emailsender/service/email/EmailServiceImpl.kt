package dev.andzwp.emailsender.service.email

import dev.andzwp.emailsender.dto.Email
import dev.andzwp.emailsender.service.email.EmailService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl() : EmailService {

    private val logger: Log = LogFactory.getLog(this.javaClass)

    override fun send(email: Email) {
        logger.info("Email successfully send")
    }
}