package com.roger.notice.controller;

import com.roger.notice.entity.Notice;
import com.roger.notice.service.impl.NoticeServiceImpl;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend/notice")
public class NoticeControllerBackEnd {

    @Autowired
    public NoticeServiceImpl noticeService;

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



}
