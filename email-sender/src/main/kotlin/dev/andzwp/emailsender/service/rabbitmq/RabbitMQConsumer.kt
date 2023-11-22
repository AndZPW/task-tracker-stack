package dev.andzwp.emailsender.service.rabbitmq

import dev.andzwp.emailsender.dto.Email
import dev.andzwp.emailsender.service.email.EmailService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class RabbitMQConsumer(@Qualifier("default-impl") private val emailService: EmailService) {

    @RabbitListener(queues = ["\${rabbitmq.queue.name}"])
    fun consumeEmail(email: Email) {
        emailService.send(email)
    }
}