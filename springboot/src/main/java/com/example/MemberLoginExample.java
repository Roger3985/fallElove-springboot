package com.example;

import com.roger.member.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * 這個類別代表一個登錄的範例。
 * 它可以包含與會原登錄相關的功能和邏輯。
 *
 * @Author Roger
 */

@Component
public class MemberLoginExample {

    // TODO: 添加與會員登錄相關的方法與屬性 (TODO 是一種常見的註釋或標記，用來提醒開發者未來需要進行的工作、未完成的功能或需要改善的地方。)

    /**
     * 前往登入頁面。
     * 此方法處理 HTTP GET 請求到 '/frontend/member/loginMember' URL 路徑。
     *
     * @return 要呈現的視圖名稱 "loginMember.html"。
     */
    @GetMapping("/frontend/member/loginMember")
    public String goToLoginMember() {
        return "frontend/member/loginMember";
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

        // 從 HTTP 會話中獲取當前已登入的會員資料
        Member myData = (Member) session.getAttribute("loginsuccess");

        // 如果會員未登錄，重定向到登錄頁面
        if (myData == null) {
            return "redirect:/frontend/member/loginMember";
        }

        // 將會員資料添加到模型中
        modelMap.addAttribute("myData", myData);

        // 返回要呈現的視圖名稱
        return "frontend/member/oneMember";
    }
}
