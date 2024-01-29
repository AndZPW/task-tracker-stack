package dev.andzwp.taskservice.service;

import dev.andzwp.taskservice.dto.DailyReportDTO;

public interface DailyReportProducer {
    void sendDailyReport(DailyReportDTO dto);
}
