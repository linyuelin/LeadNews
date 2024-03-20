package com.heima.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;



@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {
	
    /**
     * 文章をロードする
     * @param dto
     * @param type 1 もっと   2 最新
     * @return
     */
	public List<ApArticle> loadArticleList(ArticleHomeDto dto , Short type);
}
