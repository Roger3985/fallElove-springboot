package com.roger.member.service.impl;

import com.roger.member.dao.MemberRepository;
import com.roger.member.entity.Member;
import com.roger.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    /**
     * 自動裝配的 MemberRepository，用於執行會員相關的資料庫操作。
     */
    @Autowired
    private MemberRepository memberRepository;

    /**
     * 自動裝配的 StringRedisTemplate，用於執行與 Redis 資料庫操作，特別是針對字串類型的資料。
     */
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Member register(Member member) {
        return null;
    }

    /**
     * 登入會員
     */
    @Override
    public Member login(String memMail, String memPwd) {
        Member getMemMail = memberRepository.findByMemMail(memMail);
        if (getMemMail == null) return getMemMail;

        // 核對密碼
        getMemMail = (hashPassword(memPwd).equals(getMemMail.getMemPwd())) ? getMemMail : null;
        return getMemMail;
    }

    @Override
    public Member findByNo(Integer memNo) {
        return null;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * 哈希(MD5)密碼加密
     */
    @Override
    public String hashPassword(String memPwd) {

        // 將 memPwd 加上前綴 "Fall" 和後綴 "Love" 進行簡易密碼加密
        memPwd = "Fall" + memPwd + "Love";

        // 根據 MD5 演算法生成 MessageDigest 物件
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            // 將字符串 memPwd 轉換為字節陣列
            byte[] srcBytes = memPwd.getBytes();
            // 使用 srcBytes 更新摘要
            md5.update(srcBytes);
            // 完成哈希演算法計算，得到 result
            byte[] resultBytes = md5.digest();

            // 將字節陣列轉換為十六進制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : resultBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // 返回十六進制字符串
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 處理忘記密碼的功能
     */
    @Override
    public boolean forgetPassword(String memMail) {

        if (memberRepository.existsByMemMail(memMail)) {
            String authCode = getAuthCode();
        }
        return false;
    }

    /**
     * 產生一個隨機的密碼。
     *
     * 此方法會產生一個長度為 8 的隨機密碼。
     * 密碼可能包含大小寫英文字母、小寫英文字母和數字。
     *
     * @return 長度為 8 的隨機密碼。
     */
    private String getAuthCode() {
        // A~Z unicode 65~90
        // a~z unicode 97~122
        String random = "";
        for (int i = 1; i <= 8; i++) {
            int getChose = (int)(Math.random() * 3);
            switch (getChose) {
                case 0:
                    int getNumber = getRandom(0, 9);
                    random += String.valueOf(getNumber);
                    break;
                case 1:
                    char getEnUp = (char) getRandom(65, 90);
                    random += String.valueOf(getEnUp);
                    break;
                case 2:
                    char getEnDown = (char) getRandom(97, 122);
                    random += String.valueOf(getEnDown);
                    break;
            }
        }
        return random;
    }

    private int getRandom(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
    }


}
