package com.heima.article.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;

@Mapper
public interface ApArticleContentMapper extends BaseMapper<ApArticleContent>  {

}
