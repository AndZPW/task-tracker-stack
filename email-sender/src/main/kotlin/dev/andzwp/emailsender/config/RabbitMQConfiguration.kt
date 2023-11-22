package dev.andzwp.emailsender.config

import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfiguration(@Value("\${rabbitmq.queue.name}") private val queueName: String) {
    @Bean
    fun queue(): Queue = Queue(queueName)

}