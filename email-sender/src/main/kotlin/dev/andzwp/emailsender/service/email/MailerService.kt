package dev.andzwp.emailsender.service.email

import dev.andzwp.emailsender.dto.Email

interface MailerService {
    fun sendSimpleMessage(email: Email)
}