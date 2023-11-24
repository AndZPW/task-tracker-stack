package dev.andzwp.emailcreator.service.email;

import dev.andzwp.emailcreator.dto.Email;
import dev.andzwp.emailcreator.dto.User;
import dev.andzwp.emailcreator.service.email.EmailCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationEmailCreator implements EmailCreator<User> {

    @Override
    public Email createEmail(User user) {
        final var title = "Thank you for registration in our service!";
        final var content = "You login for authorization" + user.email();
        var email = new Email(user.email(), title, content);
        log.info("Email for user with {} successfully created", email.address());
        return email;
    }
}
