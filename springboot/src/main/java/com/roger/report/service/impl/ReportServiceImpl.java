package com.roger.report.service.impl;

import com.roger.report.entity.Report;
import com.roger.report.repository.ReportRepository;
import com.roger.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    /**
     * 添加新的回覆檢舉。
     */
    @Override
    public void addReport(Report report) {
        report.setReportTime(Timestamp.valueOf(LocalDateTime.now()));
        reportRepository.save(report);
    }

    /**
     * 獲取指定檢舉文章編號的回覆檢舉。
     */
    @Override
    public Report getOneReport(Integer reportNo) {

        // 使用 reportRepository 查找給定的 reportNo
        Optional<Report> optional = reportRepository.findById(reportNo);

        // 返回查找結果
        return optional.orElse(null);
    }

    /**
     * 獲取所有回覆檢舉列表。
     */
    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }
}
