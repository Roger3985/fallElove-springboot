package com.roger.member.controller;

import com.roger.member.entity.Member;
import com.roger.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/backend/member")
public class MemberControllerBackEnd {

    @Autowired
    private MemberService memberService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 顯示所有會員列表的頁面。
     * 此方法處理 HTTP GET 請求到 '/backend/member/memberlist' URL 路徑，
     * 返回呈現所有會員列表的視圖名稱。
     *
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @return 要呈現的視圖名稱 "listAllMember.html"。
     */
    @GetMapping("/memberlist")
    public String showList(ModelMap modelMap) {
        return "backend/member/listAllMember";
    }

    /**
     * 提供所有會員資料列表供試圖渲染使用。
     *
     * @return 包含所有會員的列表。
     */
    @ModelAttribute("memListData")
    protected List<Member> referenceListData() {
        List<Member> list = memberService.findAll();
        return list;
    }


}
