package dev.andzwp.emailcreator.service.email;

import dev.andzwp.emailcreator.dto.Email;
import dev.andzwp.emailcreator.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationEmailCreator implements EmailCreator<User> {

    @Override
    public Email createEmail(User user) {
        var address = user.email();
        var title = "Thank you for registration in our service!";
        var content = "You login for authorization" + address;
        var email = new Email(address, title, content);
        log.info("Email for user with {} successfully created", email.address());
        return email;
    }
}
