package dev.andzwp.emailcreator.service.email;

import dev.andzwp.emailcreator.dto.Email;
import dev.andzwp.emailcreator.dto.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DailyStatisticEmailCreator implements EmailCreator<Task> {
    @Override
    public Email createEmail(Task entity) {
        var address = entity.address();
        var title = "Daily report";
        var content = "Today " + entity.amountOfFinishedTasks() + "tasks have been finished.\n"
                + entity.amountOfUnFinishedTasks() + "tasks is left.";
        var email = new Email(address, title, content);
        log.info("Email with daily statistic for user with {} successfully created", email.address());
        return email;
    }
}
