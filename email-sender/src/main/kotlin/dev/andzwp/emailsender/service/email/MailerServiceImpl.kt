package dev.andzwp.emailsender.service.email

import dev.andzwp.emailsender.dto.Email
import dev.andzwp.emailsender.service.email.MailerService
import org.springframework.stereotype.Service

@Service("default-impl")
class MailerServiceImpl: MailerService {
    override fun sendSimpleMessage(email: Email) {
        TODO("Not yet implemented")
    }
}