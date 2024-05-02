package com.roger.report.controller;

import com.ren.administrator.entity.Administrator;
import com.roger.report.entity.Report;
import com.roger.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/backend/report")
public class ReportControllerBackEnd {

    @Autowired
    ReportService reportService;

    /**
     * 處理 GET 請求以顯示所有回覆檢舉的列表。
     * 當收到對 `/listAllReport` 的 GET 請求時，此方法將返回一個視圖名稱
     * `backend/report/listAllReport`，以顯示所有回覆檢舉的列表。
     *
     * @param model 用於向視圖中傳遞數據的模型物件。
     * @return 要渲染的視圖名稱 `backend/report/listAllReport`。
     */
    @GetMapping("/listAllReport")
    public String listAllReport(Model model) {
        return "backend/report/listAllReport";
    }

    /**
     * 設置模型屬性 `reportListData` 為所有回覆檢舉的列表。
     * 此方法調用 `reportService.getAll()` 獲取所有回覆檢舉的列表，並將該列表設置為模型屬性 `reportListData`。
     * 這樣，所有的報告列表可以在相關的視圖中使用。
     *
     * @return 包含所有回覆檢舉的列表。
     */
    @ModelAttribute("reportListData")
    protected List<Report> referenceListData() {
        List<Report> list = reportService.getAll();
        return list;
    }

    /**
     * 更新回覆檢舉。
     * 此方法接受一個 `@Valid` 標註的 `Report` 對象，並使用回覆檢舉 (`reportService`) 來添加或更新回覆檢舉。
     * 在成功更新回覆檢舉後，方法會將用戶重定向到 `/backend/report/listAllReport` 頁面。
     *
     * @param report   要更新的回覆檢舉檢舉，該物件會進行驗證。
     * @param modelMap 用於在前端顯示消息的模型。
     * @param session  HTTP會話物件，用於獲取當前的登錄管理員（`ValidAdministrator`）。
     * @return 重定向到 `/backend/report/listAllReport` 頁面。
     * @throws IOException 如果在操作過程中發生 I/O 錯誤時引發的異常。
     */
    @PostMapping("/updateReport")
    public String updateReport(@Valid Report report, ModelMap modelMap, HttpSession session) throws IOException {

        // 從會話中獲取當前登錄的管理員
        Administrator administrator = (Administrator) session.getAttribute("ValidAdministrator");

        // 設置管理回覆檢舉的管理員為當前登錄的管理員
        report.setAdministrator(administrator);

        // 更新回覆檢舉
        reportService.addReport(report);

        // 在模型中添加成功消息去顯示到前端
        modelMap.addAttribute("success", "- (修改成功)");

        // 獲取更新後的回覆檢舉
        report = reportService.getOneReport(Integer.valueOf(report.getReportNo()));

        // 在模型中添加回覆檢舉物件以供前端顯示
        modelMap.addAttribute("report", report);

        // 重定向到回覆檢舉列表頁面
        return "redirect:/backend/report/listAllReport";
    }
}
