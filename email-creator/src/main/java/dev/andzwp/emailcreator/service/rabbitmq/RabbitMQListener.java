package dev.andzwp.emailcreator.service.rabbitmq;

import dev.andzwp.emailcreator.dto.User;
import dev.andzwp.emailcreator.service.email.EmailCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final RabbitMQProducer rabbitMQProducer;

    private final EmailCreator<User> emailCreator;

    @RabbitListener(queues = {"${rabbit.queue.name}"})
    public void sendMessageToEmailSender(User user) {
        var email = emailCreator.createEmail(user);
        rabbitMQProducer.sendMessage(email);
    }
}
