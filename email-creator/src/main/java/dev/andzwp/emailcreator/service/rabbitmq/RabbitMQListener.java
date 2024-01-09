package dev.andzwp.emailcreator.service.rabbitmq;

import dev.andzwp.emailcreator.dto.User;

public interface RabbitMQListener<T> {
    void sendMessageToEmailSender(T entity);
}
