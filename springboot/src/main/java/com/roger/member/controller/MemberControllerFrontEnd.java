package com.roger.member.controller;

import com.roger.member.entity.Member;
import com.roger.member.entity.unique.Create;
import com.roger.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/frontend/member")
public class MemberControllerFrontEnd {

    @Autowired
    private MemberService memberService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 前往註冊會員頁面。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/addMember' URL 路徑，
     * 創建一個新的 'Member' 實例，並將其添加到 'ModelMap' 中，
     * 前端試圖可以訪問和使用這個會員物件。
     *
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @return 要呈現的視圖名稱 "addMember.html"。
     */
    @GetMapping("/addMemberData")
    public String addMemberData(ModelMap modelMap) {
        Member member = new Member();
        modelMap.addAttribute("member", member);
        return "frontend/member/addMember";
    }

    /**
     * 前往單個會員資料的頁面。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/memberData' URL 路徑，
     * 從會話中獲取當前已登入的會員資料並將其添加到 'ModelMap' 中。
     *
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @param session session HTTP 會話物件，用來儲存和訪問當前已經登入的會員。
     * @return 要呈現的視圖名稱 "oneMember.html"。
     */
    @GetMapping("/memberData")
    public String memberData(ModelMap modelMap, HttpSession session) {
        Member myData = (Member) session.getAttribute("loginsuccess");
        modelMap.addAttribute("myData", myData);
        return "frontend/member/oneMember";
    }

    /**
     * 處理導向會員中心的請求。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/memberCenter' URL 路徑，
     * 並請求重定向至會員資料頁面("/frontend/member/memberData")。
     *
     * @return 重定向至會員資料頁面("/frontend/member/memberData")。
     */
    @GetMapping("memberCenter")
    public String memberCenter() {
        return "redirect:frontend/member/memberData";
    }


    /**
     * 前往登入頁面。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/loginMember' URL 路徑。
     *
     * @return 要呈現的視圖名稱 "loginMember.html"。
     */
    @GetMapping("/loginMember")
    public String logicMember() {
        return "frontend/member/loginMember";
    }

    /**
     * 前往忘記密碼頁面。
     * 此方法處理 HTTP GET 請求 'frontend/member/forgetPassword' URL 路徑。
     *
     * @param modelMap 模型映射，用於存放任何傳遞給視圖的屬性。
     * @return 要呈現的視圖名稱 "frontend/member/forgetPassword"。
     */
    @GetMapping("/forgetPassword")
    public String forgetPassword(ModelMap modelMap) {
        return "frontend/member/forgetPassword";
    }


    /**
     * 處理會員登入請求。
     * 此方法處理 HTTP POST 請求到 '/frontend/member/loginPage' URL 路徑，
     * 接收會員的電子郵件和密碼，嘗試進行登入操作。
     * 如果登入成功，將會員的訊息儲存到會話當中，並重定向到原始請求的 URI。
     * 如果登入失敗，返回錯誤消息並繼續停留在登入頁面。
     *
     * @param memMail 會員的電子郵件。
     * @param memPwd 會員的密碼。
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @param session 會話物件，用於儲存和訪問會員訊息。
     * @param response 回應物件，用於處理重定向。
     * @return 如果登入失敗，返回登入頁面名稱；如果成功，方法返回 null 並重定向到原始請求的 URI。
     */
    @PostMapping("/loginPage")
    public String loginPage(@ModelAttribute("mail") String memMail, @ModelAttribute("password") String memPwd,
                            ModelMap modelMap, HttpSession session, HttpServletResponse response) {

        // 獲取重定向的 URI 或設置默認值
        String uri = session.getAttribute("URI") == null ? "/" : session.getAttribute("URI").toString();

        // 獲取項目的根路徑
        String projectUri = session.getServletContext().getContextPath();

        // 檢查郵件和密碼是否為空
        if (memMail.isEmpty() || memPwd.isEmpty()) {
            modelMap.addAttribute("message", "信箱或密碼不能空白，請重新輸入!");
            return "frontend/member/loginMember";
        }

        // 嘗試登入
        Member loginData = memberService.login(memMail, memPwd);
        if (loginData != null) {
            // 登入成功，將會員信息儲存到會話中
            session.setAttribute("loginsuccess", loginData);
            session.removeAttribute("URI");

            // 重定向到原始請求的 URI
            try {
                response.sendRedirect(projectUri + uri);
                return null;
            } catch (IOException e) {
                // 處理重定向的 IOException
                e.printStackTrace();
            }
        } else {
            // 登入失敗，返回錯誤消息
            modelMap.addAttribute("message", "信箱或密碼輸入錯誤，請重新輸入!");
            return "frontend/member/loginMember";
        }

        // 如果程序運行到這裡，說明發生了異常情況
        return "frontend/member/loginMember";
    }

}
