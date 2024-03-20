package com.heima.article.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle>  implements ApArticleService{
   
	@Autowired
	private  ApArticleMapper apArticleMapper;
	
    private final static short MAX_PAGE_SIZE = 50;
    /**
     * 文章をロードする
     * @param dto
     * @param type 1 もっと   2 最新
     * @return
     */
	@Override
	public ResponseResult load(ArticleHomeDto dto, Short type) {
		
		//パラメータ検証する
		// 1.サイズのチェック
		  Integer size = dto.getSize();
		  if(size == null || size == 0) {
			  size = 10;
		  }
		  
		  size = Math.min(size,MAX_PAGE_SIZE);
		// 2.type
		  if(!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
			  type  = ArticleConstants.LOADTYPE_LOAD_MORE ;
		  }
		  
		// 3.チャンネル
		  if (StringUtils.isBlank(dto.getTag())) {
			  dto.setTag(ArticleConstants.DEFAULT_TAG);
		  }
		// 4.時間
		  if(dto.getMaxBehotTime() == null )dto.setMaxBehotTime(new Date());
		  if(dto.getMinBehotTime() == null )dto.setMinBehotTime(new Date());
		  
		
		
		//クエリする
		List<ApArticle> loadArticleList = apArticleMapper.loadArticleList(dto, type);
		
	    // 結果を返す
		return ResponseResult.okResult(loadArticleList);
	}
	
	
	

}
