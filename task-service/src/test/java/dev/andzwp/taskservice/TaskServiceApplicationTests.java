package dev.andzwp.taskservice;

import dev.andzwp.taskservice.dto.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskServiceApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(Status.ACTIVE);
    }

}
