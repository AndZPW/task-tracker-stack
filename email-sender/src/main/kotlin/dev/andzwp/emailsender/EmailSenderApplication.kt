package dev.andzwp.emailsender

import com.rabbitmq.client.AMQP
import org.springframework.amqp.core.Queue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@SpringBootApplication
class EmailSenderApplication

fun main(args: Array<String>) {
    runApplication<EmailSenderApplication>(*args)
}


