package com.roger.columnarticle.service;

import com.roger.columnarticle.entity.ColumnArticle;

import java.util.List;

public interface ColumnArticleService {

    /**
     * 查找所有專欄文章。
     *
     * @return 包含所有專欄文章的 List<ColumnArticle> 列表。
     */
    public List<ColumnArticle> findAll();

    /**
     * 獲取指定文章編號的文章專欄。
     * 此方法使用 `reportRepository` 查找給定的 `artNo`，
     * 並返回一個 `Optional<Report>`。
     *
     * @param artNo 要查找的文章編號。
     * @return `Optional<ColumnArticle>` 包含找到的 `ColumnArticle` 物件，或 `Optional.empty()` 表示找不到。
     */
    public ColumnArticle getOneColumnArticle(Integer artNo);
}
