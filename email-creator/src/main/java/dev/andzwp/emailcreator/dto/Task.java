package dev.andzwp.emailcreator.dto;

import lombok.Builder;

@Builder
public record Task(String address, Long amountOfFinishedTasks, Long amountOfUnFinishedTasks) {
}
