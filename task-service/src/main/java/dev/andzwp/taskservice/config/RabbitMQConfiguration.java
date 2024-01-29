package dev.andzwp.taskservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.task.queue}")
    private String taskQueueName;

    @Value("${rabbitmq.task.key}")
    private String taskRoutingKey;


    @Bean
    public Queue queue() {
        return new Queue(taskQueueName, true);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(exchangeName);
    }


    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(taskRoutingKey)
                .noargs();
    }

}
