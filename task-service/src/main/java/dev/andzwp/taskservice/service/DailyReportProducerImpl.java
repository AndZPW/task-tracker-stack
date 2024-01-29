package dev.andzwp.taskservice.service;

import dev.andzwp.taskservice.dto.DailyReportDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DailyReportProducerImpl implements DailyReportProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;


    @Value("${rabbitmq.task.key}")
    private String taskRoutingKey;


    @Override
    public void sendDailyReport(DailyReportDTO dto) {
        rabbitTemplate.conv










    ertAndSend(exchangeName,taskRoutingKey,dto);
    }
}
