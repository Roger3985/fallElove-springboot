package com.roger.notice.service;

import com.roger.member.entity.Member;
import com.roger.notice.entity.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 從資料庫中查找所有符合指定條件的 NoticeVO 實例。
     *
     * @return List<NoticeVO> 包含所有符合篩選條件的 NoticeVO 實例的列表。
     *         如果沒有符合條件的記錄，則返回空列表。
     */
    List<Notice> getAll();

    public Notice getOneByMember(Member member);
}
