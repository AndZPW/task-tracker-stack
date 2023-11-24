package dev.andzwp.emailcreator.service.email;

import dev.andzwp.emailcreator.dto.Email;
import dev.andzwp.emailcreator.dto.User;

public interface EmailCreator<T> {
    Email createEmail(T entity);
}
