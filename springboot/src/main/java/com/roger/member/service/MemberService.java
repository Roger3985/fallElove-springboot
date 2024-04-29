package com.roger.member.service;

import com.roger.member.entity.Member;
import java.util.List;

public interface MemberService {

    /**
     * 註冊新會員。
     *
     * @param member 新會員的資料，封裝在 MemberVO 物件中。
     * @return 註冊成功後的 MemberVO 物件，其中可能包含更新的會員資訊 (例如:自動生成的會員編號)
     */
    public Member register(Member member);

    /**
     * 登入會員並驗證其帳號和密碼。
     *
     * @param memMail 會員的註冊信箱。
     * @param memPwd 會員的註冊密碼。
     * @return 登入成功後的 MemberVO 物件，其中包含該會員的資料。
     */
    public Member login(String memMail, String memPwd);

    /**
     * 根據會員編號查詢會員資料
     *
     * @param memNo 要查詢的會員編號
     * @return 找到相對應的會員，則返回 MemberVO 物件。
     */
    public Member findByNo(Integer memNo);

    /**
     * 查找所有會員。
     *
     * @return 包含所有會員的 List<Member> 列表。
     */
    public List<Member> findAll();

    /**
     * 使用哈希演算法對密碼進行加密。
     *
     * 該方法接收一個明文密碼，並使用安全的哈希算法（例如:MD5）對其進行哈希加密。
     * 哈希後的密碼可以安全地存儲在資料庫中。
     *
     * @param memPwd 被哈希加密後的密碼。
     * @return 哈希加密後的密碼，這個密碼是安全的，適合在資料庫中存儲。
     */
    public String hashPassword(String memPwd);

    /**
     * 處理忘記密碼的功能。
     *
     * 此方法接受會員的註冊信箱，並對會員的密碼進行重置或發送重置密碼的相關訊息。
     *
     * @param memMail 會員的註冊信箱。
     * @return 如果重置密碼的請求成功，則返回 true;否則返回 false
     */
    public boolean forgetPassword(String memMail);




}
