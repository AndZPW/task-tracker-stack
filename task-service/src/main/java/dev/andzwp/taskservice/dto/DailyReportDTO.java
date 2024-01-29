package dev.andzwp.taskservice.dto;

public record DailyReportDTO(String emailAddress, Long amountOfFinishedTasks, Long amountOfUnFinishedTasks) {
}
