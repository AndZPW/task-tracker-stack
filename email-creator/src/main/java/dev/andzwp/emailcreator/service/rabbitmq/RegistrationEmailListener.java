package dev.andzwp.emailcreator.service.rabbitmq;

import dev.andzwp.emailcreator.dto.User;
import dev.andzwp.emailcreator.service.email.EmailCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationEmailListener implements RabbitMQListener<User> {

    private final RabbitMQProducer rabbitMQProducer;

    private final EmailCreator<User> emailCreator;

    @Override
    @RabbitListener(queues = {"${rabbit.user.queue}"})
    public void sendMessageToEmailSender(User user) {
        var email = emailCreator.createEmail(user);
        rabbitMQProducer.sendMessage(email);
    }
}
