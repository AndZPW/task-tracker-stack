package dev.andzwp.emailsender.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfiguration(
    @Value("\${rabbitmq.queue.name}") private val queueName: String,
    @Value("\${rabbitmq.exchange.name}") private val topicExchangeName: String,
    @Value("\${rabbitmq.routing.key}") private val routingKey: String
) {
    @Bean
    fun queue(): Queue =
        Queue(queueName)

    @Bean
    fun exchange(): TopicExchange =
        TopicExchange(topicExchangeName)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding =
        BindingBuilder.bind(queue).to(exchange).with(routingKey)
}