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
     * 編輯會員資料
     * 此方法接收一個 'Member' 類型的物件，表示需要更新的會員資料。
     * 方法執行後，將會根據提供的會員資料更新相應的會員訊息。
     *
     * @param newData 要編輯的會員物件，包含了需要更新的會員訊息。
     * @return 更新後的會員物件。
     */
    public Member edit(Member newData);

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
     * 信箱驗證。
     * 此方法負責發送信箱驗證的郵件。
     * 方法接受驗證碼、主題、內容和驗證ID作為參數，並使用這些信息向用戶發送驗證郵件。
     *
     * @param mail 驗證碼，用於郵件中進行身份驗證。
     * @param subject 郵件主題，描述郵件中的內容。
     * @param text 郵件內容，包含驗證相關信息。
     * @param verifyID 驗證ID，用於識別驗證操作。
     * @return 郵件發送結果，通常為狀態描述或識別驗證狀態的字符串。
     */
    public boolean verifyMail(String mail, String subject, String text, String verifyID);

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
