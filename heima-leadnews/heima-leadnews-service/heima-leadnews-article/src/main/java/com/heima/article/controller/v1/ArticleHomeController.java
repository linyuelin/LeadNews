package com.heima.article.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleHomeController {

	@Autowired
	private ApArticleService apArticleService;
	
	/**
	 * ホームページ
	 * @param dto
	 * @return
	 */

    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }
    
    /**
     * もっとロードする
     * @param dto
     * @return
     */
    @PostMapping("/loadmore")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto dto) {
    	return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_MORE);
    }
    
    /**
     * 最新をロードする
     * @param dto
     * @return
     */
    @PostMapping("/loadnew")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto dto) {
    	return apArticleService.load(dto, ArticleConstants.LOADTYPE_LOAD_NEW);
    }
}