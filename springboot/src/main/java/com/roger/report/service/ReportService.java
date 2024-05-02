package com.roger.report.service;

import com.roger.report.entity.Report;

import java.util.List;

public interface ReportService {

    /**
     * 添加新的回覆檢舉。
     * 此方法接受一個 `Report` 物件，並將其添加到系統中。
     *
     * @param report 要添加的 `Report` 物件。
     */
    public void addReport(Report report);

    /**
     * 獲取指定檢舉文章編號的回覆檢舉。
     * 此方法使用 `reportRepository` 查找給定的 `reportNo`，
     * 並返回一個 `Optional<Report>`。
     *
     * @param reportNo 要查找的檢舉文章編號。
     * @return `Optional<Report>` 包含找到的 `Report` 物件，或 `Optional.empty()` 表示找不到。
     */
    public Report getOneReport(Integer reportNo);

    /**
     * 獲取所有回覆檢舉列表。
     * 此方法返回系統中所有回覆檢舉的列表。
     *
     * @return 包含所有回覆檢舉的列表。如果沒有回覆檢舉，則返回一個空的回覆檢舉列表。
     */
    public List<Report> getAll();
}
