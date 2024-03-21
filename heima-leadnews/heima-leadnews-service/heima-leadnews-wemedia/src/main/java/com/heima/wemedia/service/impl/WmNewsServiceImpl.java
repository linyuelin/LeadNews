package com.heima.wemedia.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMapper;
import com.heima.wemedia.mapper.WmchannelMapper;
import com.heima.wemedia.service.WmMaterialService;
import com.heima.wemedia.service.WmNewsService;
import com.heima.wemedia.service.WmchannelService;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper,WmNews> implements WmNewsService {@Override
	
	
	/**
	 * 全てのニュースを
	 * @param dto
	 * @return
	 */
	public ResponseResult findAll(WmNewsPageReqDto dto) {
		
	// パラメータのチェック
      dto.checkParam();
      
	// ステータス
	   IPage page = new Page(dto.getPage(),dto.getSize());
	   LambdaQueryWrapper<WmNews> lambdaQueryWrapper = new LambdaQueryWrapper<WmNews>();
       
	   if(dto.getStatus() != null) {
	   lambdaQueryWrapper.eq(WmNews::getStatus, dto.getStatus());
	   }
	// 開始時間 &　終わり時間
	   if(dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
		   
		   lambdaQueryWrapper.between(WmNews::getPublishTime, dto.getBeginPubDate(), dto.getEndPubDate());
	   }
   
    //チャンネルID
      if(dto.getChannelId() != null) {
    	  lambdaQueryWrapper.eq(WmNews::getChannelId, dto.getChannelId());
      }
    
    //キーワード
     if(StringUtils.isNotBlank(dto.getKeyword())) {
    	 lambdaQueryWrapper.like(WmNews::getTitle, dto.getKeyword());
    	 
     }
      /// TODO:
     //ログイン中の方
     lambdaQueryWrapper.eq(WmNews::getUserId, WmThreadLocalUtil.getUser().getId());
     
     //公開時間で降順に検索
     lambdaQueryWrapper.orderByDesc(WmNews::getPublishTime);
     
     
     
	  page =  page(page,lambdaQueryWrapper);
     
	  ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
	  responseResult.setData(page.getRecords());
	return responseResult ;
	}
	
	
	
	
}
