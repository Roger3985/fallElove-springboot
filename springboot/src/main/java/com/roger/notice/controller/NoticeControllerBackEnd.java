package com.roger.notice.controller;

import com.roger.notice.entity.Notice;
import com.roger.notice.service.impl.NoticeServiceImpl;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/backend/notice")
public class NoticeControllerBackEnd {

    @Autowired
    public NoticeServiceImpl noticeService;

    /**
     * 前往新增頁面。
     *
     * @param modelMap 視圖模型，用於在頁面中儲存和傳遞資料。
     * @return 返回 "backend/notice/addNotice" 視圖名稱，用於渲染新增通知頁面。
     */
    @GetMapping("/addNoticeData")
    public String addNoticeData(ModelMap modelMap) {
        modelMap.addAttribute("notice", new Notice());
        return "backend/notice/addNotice";
    }

    /**
     * 處理新增通知的請求。
     *
     * @param notice 接收新增通知的資料物件。
     * @param result 用於驗證輸入資料的結果物件。
     * @param modelMap 視圖模型，用於在頁面儲存和傳遞資料。
     * @return 如果存在錯誤，返回 "backend/notice/addNotice" 視圖名稱繼續顯示新增通知頁面；如果成功添加通知，則重定向到通知列表頁面。
     * @throws IOException 如果在處理過程中發生 I/O 錯誤。
     */
    @PostMapping("/addNotice")
    public String addNotice(@Valid Notice notice, BindingResult result, ModelMap modelMap) throws IOException {
        System.out.println(notice.getNotContent());
        if (result.hasErrors()) {
            return "backend/notice/addNotice";
        }

        noticeService.addNotice(notice);
        return "redirect:/backend/notice/noticelist";
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

    /**
     * 設置查詢全部通知列表的模型屬性。
     *
     * @return 返回查詢全部通知列表的模型屬性。
     */
    @ModelAttribute("noticeListData")
    protected List<Notice> referenceListData() {
        List<Notice> list = noticeService.getAll();
        return list;
    }



}
