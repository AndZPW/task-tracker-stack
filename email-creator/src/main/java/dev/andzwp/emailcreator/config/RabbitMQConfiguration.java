package dev.andzwp.emailcreator.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbit.email.queue}")
    private String queueName;

    @Value("${rabbit.email.exchange}")
    private String topicExchangeProducerName;


    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(topicExchangeProducerName);
    }

    @Bean
    public Binding binding(Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}

