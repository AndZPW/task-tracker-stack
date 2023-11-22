package dev.andzwp.emailsender.service.email

import dev.andzwp.emailsender.dto.Email

interface EmailService {
    fun send(email: Email)
}