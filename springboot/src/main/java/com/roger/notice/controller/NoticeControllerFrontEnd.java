package com.roger.notice.controller;

import com.roger.notice.entity.Notice;
import com.roger.notice.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/frontend/notice")
public class NoticeControllerFrontEnd {

    @Autowired
    private NoticeServiceImpl noticeService;

    // 全部json到前端
    @GetMapping("/getAllNotice")
    public ResponseEntity<List<Notice>> getAllNotices() {
        Notice notice = new Notice();
        List<Notice> notices = noticeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(notices);
    }

    /**
     * 顯示所有通知的列表視圖頁面。
     *
     * @param modelMap 視圖模型，用於在頁面中存儲和傳遞數據。
     * @return 返回 "backend/notice/listAllNotice" 視圖名稱，用於渲染通知列表頁面。
     */
    @GetMapping("/noticelist")
    public String showlist(ModelMap modelMap) {
        return "backend/notice/listAllNotice";
    }

    // 設置查詢全部屬性
    @ModelAttribute("noticeListData")
    protected List<Notice> referenceListData() {
        List<Notice> list = noticeService.getAll();
        return list;
    }
}
