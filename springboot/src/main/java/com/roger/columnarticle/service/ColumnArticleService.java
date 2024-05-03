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
}
