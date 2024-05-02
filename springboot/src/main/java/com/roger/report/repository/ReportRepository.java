package com.roger.report.repository;

import com.roger.member.entity.Member;
import com.roger.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    /**
     * 查找與指定的相關的回覆檢舉列表。
     * 此方法根據給定的 `Member` 物件查找與該會員相關聯的所有 `Report` 物件。
     *
     * @param member 要查找其相關回覆檢舉的 `Member` 物件。
     * @return 與指定會員相關聯的 `Report` 列表。如果沒有找到任何回覆檢舉，則返回空列表。
     */
    public List<Report> findByMember(Member member);
}
