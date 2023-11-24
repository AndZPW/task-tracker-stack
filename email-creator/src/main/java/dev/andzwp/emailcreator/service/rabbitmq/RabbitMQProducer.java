package dev.andzwp.emailcreator.service.rabbitmq;

import dev.andzwp.emailcreator.dto.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.producer.topic}")
    private String topicExchangeName;

    @Value("${rabbit.producer.routing-key}")
    private String routingKey;

    public void sendMessage(Email email) {
        try {
            rabbitTemplate.convertAndSend(topicExchangeName, routingKey, email);
            log.info("Email sent to the email sender");
        } catch (RuntimeException e) {
            log.error("The exception with following message {}", e.getMessage());
        }
    }
}
