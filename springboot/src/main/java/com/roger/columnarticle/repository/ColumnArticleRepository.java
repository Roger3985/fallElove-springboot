package com.roger.columnarticle.repository;

import com.roger.columnarticle.entity.ColumnArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnArticleRepository extends JpaRepository<ColumnArticle, Integer> {
}
