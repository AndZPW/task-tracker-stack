package dev.andzwp.taskservice.model;


import dev.andzwp.taskservice.dto.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "content", nullable = false, length = 4096)
    private String content;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "last_modified", nullable = false)
    private LocalDateTime time;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
