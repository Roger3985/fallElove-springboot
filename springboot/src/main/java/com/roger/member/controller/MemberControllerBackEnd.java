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
     * 處理添加新會員的 Post 請求
     *
     * @param model 包含模型資料的 'ModelMap' 物件，用於在視圖和控制之間傳遞資料。
     * @return 返回一個視圖名稱，指定要呈現的前端頁面，這裡是 "frontend/member/addMem"。
     */
    @PostMapping("/addMem")
    public String addDate(ModelMap model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "frontend/member/addMem";
    }

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
