package dev.andzwp.emailcreator.service.rabbitmq;

import dev.andzwp.emailcreator.dto.Task;
import dev.andzwp.emailcreator.dto.User;
import dev.andzwp.emailcreator.service.email.EmailCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyStatisticListener implements RabbitMQListener<Task> {

    private final RabbitMQProducer rabbitMQProducer;

    private final EmailCreator<Task> emailCreator;

    @Override
    @RabbitListener(queues = {"${rabbit.task.queue}"})
    public void sendMessageToEmailSender(Task entity) {
        var email = emailCreator.createEmail(entity);
        rabbitMQProducer.sendMessage(email);
    }
}
