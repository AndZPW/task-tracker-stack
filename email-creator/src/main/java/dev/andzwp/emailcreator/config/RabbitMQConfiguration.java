package dev.andzwp.emailcreator.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbit.queue.name}")
    private String queueName;

    @Value("${rabbit.producer.topic}")
    private String topicExchangeProducerName;

    @Value("${rabbit.producer.routing-key}")
    private String producerRoutingKey;

    @Bean
    public Queue queue(){
        return new Queue(queueName,true);
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange(topicExchangeProducerName);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(producerRoutingKey)
                .noargs();
    }
}

