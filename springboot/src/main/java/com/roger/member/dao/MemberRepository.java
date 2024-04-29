package com.roger.member.dao;

import com.roger.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /**
     * 根據會員的電子郵件查詢會員。
     *
     * 此方法使用 JpaRepository 方法 'findByMemMail' 會以屬性 'memMail' 為設置條件搜尋，根據會員的電子郵件查詢會員。
     *
     * @param memMail 會員的電子郵件。
     * @return 如果找到相應的會員，則返回該會員。
     */
    @Transactional
    public Member findByMemMail(String memMail);

    /**
     * 判斷指定的電子郵件是否存在於會員資料庫中。
     *
     * 此方法使用 JpaRepository 的 'existsByMemMail'
     * 方法檢查會員資料庫中是否存在具有指定電子郵件的會員。
     *
     * @param memMail 要檢查的會員電子郵件。
     * @return 如果存在具有指定電子郵件的會員，則返回 true；否則返回 false。
     */
    @Transactional
    public boolean existsByMemMail(String memMail);

}
