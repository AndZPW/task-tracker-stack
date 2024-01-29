package dev.andzwp.taskservice.service;


import dev.andzwp.taskservice.dto.DailyReportDTO;
import dev.andzwp.taskservice.dto.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulingService {

    private final RabbitTemplate rabbitTemplate;

    private final TaskService taskService;

    private final Keycloak keycloak;

    private String realmName;

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void sendStatistic() {
        var users = keycloak
                .realm(realmName)
                .users()
                .list();

        if (users.isEmpty()) {
            log.info("Users list is empty");
            return;
        }

        for (var user : users) {
            var tasks = taskService.fetchAllByUserId(Long.parseLong(user.getId()));
            var amountOfCompletedTasks = tasks.stream()
                    .filter(
                            task -> task.status()
                                    .equals(Status.FINISHED)
                    )
                    .count();
            var amountOfUnCompletedTasks = tasks.size() - amountOfCompletedTasks;
            var dailyReport = new DailyReportDTO(user.getEmail(), amountOfCompletedTasks, amountOfUnCompletedTasks);
        }
    }
}
