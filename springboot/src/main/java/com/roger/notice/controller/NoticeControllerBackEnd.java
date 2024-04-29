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





}
