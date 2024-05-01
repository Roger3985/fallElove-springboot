package com.roger.notice.service;

import com.roger.member.entity.Member;
import com.roger.notice.entity.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 將新的通知添加到資料庫中。
     *
     * @param notice 要添加的 Notice 實例，包含通知的相關信息。
     * @return 新添加的 Notice 實例，帶有更新後的 ID 和其他資料庫生成的屬性。
     */
    public Notice addNotice(Notice notice);

    /**
     * 從資料庫中查找所有符合指定條件的 NoticeVO 實例。
     *
     * @return List<NoticeVO> 包含所有符合篩選條件的 NoticeVO 實例的列表。
     *         如果沒有符合條件的記錄，則返回空列表。
     */
    List<Notice> getAll();

    public Notice getOneByMember(Member member);
}
