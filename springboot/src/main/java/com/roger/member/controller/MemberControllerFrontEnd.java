package com.roger.member.controller;

import com.ren.administrator.entity.Administrator;
import com.roger.member.entity.Member;
import com.roger.member.entity.unique.Create;
import com.roger.member.service.MemberService;
import com.roger.notice.entity.Notice;
import com.roger.notice.service.NoticeService;
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
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/frontend/member")
public class MemberControllerFrontEnd {

    @Autowired
    private MemberService memberService;

    @Autowired
    private NoticeService noticeService;

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
    public String goToLoginMember() {
        return "frontend/member/loginMember";
    }

    /**
     * 前往忘記密碼頁面。
     * 此方法處理 HTTP GET 請求 '/frontend/member/forgetPassword' URL 路徑。
     *
     * @param modelMap 模型映射，用於存放任何傳遞給視圖的屬性。
     * @return 要呈現的視圖名稱 "frontend/member/forgetPassword"。
     */
    @GetMapping("/forgetPassword")
    public String forgetPassword(ModelMap modelMap) {
        return "frontend/member/forgetPassword";
    }

    /**
     * 處理會員忘記密碼
     *
     * @param memMail 使用者忘記密碼時提供的電子郵件地址。
     * @param modelMap ModelMap 物件，用於將數據傳遞到視圖層。
     * @return 如果密碼恢復過程成功，方法將重定向到登錄頁面。
     *         如果提供的電子郵件地址為空，返回到 "/frontend/member/forgetPassword" 頁面，
     *         並添加失敗消息，表示電子郵件不能為空，請重新輸入。
     *         如果提供的電子郵件地址在系統中不存在，返回到
     *         "frontend/member/forgetPassword" 頁面，
     *         並添加失敗消息，表示不存在的電子郵件，請重新輸入。
     */
    @GetMapping("/varify")
    public String varify(@RequestParam("forgetPassword") String memMail, ModelMap modelMap) {

        if (memberService.forgetPassword(memMail)) {
            return "redirect:loginMember";
        }

        if (memMail.isEmpty()) {
            modelMap.addAttribute("failMessage", "信箱不可空白，請重新輸入!");
            return "frontend/member/forgetPassword";
        }
        modelMap.addAttribute("failMessage", "不存在的信箱，請重新輸入!");
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
            if (loginData.getMemStat() == 2) {
                redisTemplate.delete("noFun" + loginData.getMemNo().toString());
                modelMap.addAttribute("noFun", "此帳號已無權限，請洽詢相關的工作人員");
                return "frontend/member/loginMember";
            }

            Notice notice = noticeService.getOneByMember(loginData);

            // 登入成功，將會員信息儲存到會話中
            session.setAttribute("loginsuccess", loginData);
            session.removeAttribute("URI");
            session.setAttribute("notice", notice);
            session.removeAttribute("URI");

            // 重定向到原始請求的 URI
            try {
                response.sendRedirect(projectUri + uri);
                return null;
            } catch (IOException e) {
                // 處理重定向的 IOException
                e.printStackTrace();
            }
        }

        // 登入失敗，返回錯誤消息
        modelMap.addAttribute("message", "信箱或密碼輸入錯誤，請重新輸入!");
        return "frontend/member/loginMember";
    }

    /**
     * 處理前往驗證的請求。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/varifiedMail' URL 路徑，
     * 並返回信箱驗證頁面的視圖路徑。
     *
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @return 信箱驗證頁面的視圖路徑。
     */
    @GetMapping("varifyMail")
    public String varifyMail(ModelMap modelMap) {
        return "frontend/member/varifiedMail";
    }

    /**
     * 信箱驗證
     *
     * @param code 用戶輸入的驗證碼。
     * @param modelMap 用於將資料添加到模型中 ModelMap 物件。
     * @param session 用於儲存會話資料的 HttpSession 物件。
     * @return 如果驗證成功，則重定向到之前的 URI ; 如果驗證失敗，則返回 "frontend/member/varifiedMail" 視圖。
     */
    @GetMapping("/sendMail")
    public String sendMail(@RequestParam("sendMail") String code, ModelMap modelMap, HttpSession session) {

        // 獲取之前儲存的URI，默認為"/"
        String uri = session.getAttribute("URI") == null ? "/" : session.getAttribute("URI").toString();
        // 從會話中獲取當前登錄的會員物件
        Member member = (Member) session.getAttribute("loginsuccess");

        // 使用 Redis 讀取資療
        String getCode = redisTemplate.opsForValue().get("templateID" + member.getMemMail());
        System.out.println("redis Data:" + getCode);

        // 檢查驗證碼是否已超時
        if (getCode == null) {
            modelMap.addAttribute("errorCode", "驗證碼已超時，請重新發送驗證碼!");
            return "frontend/member/varifiedMail";
        } else if (!code.equals(getCode)) {
            // 如果會員輸入的驗證碼不匹配，返回錯誤消息
            modelMap.addAttribute("errorCode", "驗證碼錯誤，請重新輸入!");
            return "frontend/member/varifiedMail";
        }

        System.out.println("get Code:" + code);

        // 如果驗證成功，更新會員狀態並重新定向會員
        Member varifyData = (Member) session.getAttribute("loginsuccess");
        varifyData.setMemStat((byte)1);
        varifyData = memberService.edit(varifyData);
        session.setAttribute("loginsuccess", varifyData);
        return "redirect:" + uri;
    }

    /**
     * 處理重新發送驗證碼的請求。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/reSendMail' URL 路徑，
     * 從會話中獲取當前登入的會員信息，並使用 `memberService` 發送驗證碼到會員的電子郵件地址。
     * 發送簡訊的內容包括註冊感謝和驗證碼。
     * 最後，重定向到信箱驗證頁面。
     *
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @param session 會話物件，用於儲存和訪問會員訊息。
     * @return 重定向到信箱驗證頁面的 URL。
     */
    @GetMapping("reSendMail")
    public String reSendMail(ModelMap modelMap, HttpSession session) {

        Member member = (Member) session.getAttribute("loginsuccess");

        // 發送簡訊
        memberService.verifyMail(member.getMemMail(), "Fall衣Love感謝你的註冊!", "驗證碼為:", null);

        return "redirect:frontend/member/varifyMail";
    }

    /**
     * 前往變更密碼。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/newPassword URL 路徑，
     * 返回變更密碼頁面的視圖。
     *
     * @return 變更密碼頁面的視圖名稱。
     */
    @GetMapping("/newPassword")
    public String newPassword() {
        return "frontend/member/changePassword";
    }

    /**
     * 處理會員變更密碼的請求。
     * 此方法處理 HTTP POST 請求到 '/frontend/member/changePassword' URL 路徑，
     * 接收會員的舊密碼和新密碼，嘗視進行密碼變更操作。
     * 如果密碼變更成功，將會員的訊息更新到會話當中，並重定向到會員中心頁面。
     * 如果發生錯誤，返回錯誤消息並繼續停留在變更密碼頁碼。
     *
     * @param oldPassword 會員的舊密碼。
     * @param newPassword 會員的新密碼。
     * @param modelMap 包含模型屬性的 'ModelMap'。
     * @param session 會話物件，用於儲存和訪問會員訊息。
     * @return 如果發生錯誤，返回變更密碼的頁面名稱；如果成功，方法返回 null 並重定向到會員中心頁面。
     */
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
                                 ModelMap modelMap, HttpSession session) {

        // 從會話中獲取當前登入的會員訊息
        Member member = (Member) session.getAttribute("loginsuccess");

        // 檢查舊密碼是否為空白
        if (oldPassword.isEmpty()) {
            modelMap.addAttribute("emptyOldPassword", "舊密碼不可為空白，請重新輸入!");
        } else if (!member.getMemPwd().equals(memberService.hashPassword(oldPassword))) {
            modelMap.addAttribute("oldError", "密碼輸入錯誤，請重新輸入!");
        }

        // 檢查新密碼是否為空白
        if (newPassword.isEmpty()) {
            modelMap.addAttribute("emptyNewPassword", "新密碼不可為空白，請重新輸入!");
        }

        // 如果模型存在錯誤，返回變更密碼頁面
        if (!modelMap.isEmpty()) {
            return "frontend/member/changePassword";
        }

        // 將會員的密碼設置為新密碼
        member.setMemPwd(memberService.hashPassword(newPassword));
        memberService.edit(member);

        // 更新會話中的會員訊息
        session.setAttribute("loginsuccess", member);

        // 重定向到會員中心頁面
        return "redirect:frontend/member/memberCenter";
    }

}
