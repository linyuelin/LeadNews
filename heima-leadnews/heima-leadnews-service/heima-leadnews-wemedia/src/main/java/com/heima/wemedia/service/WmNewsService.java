package com.heima.wemedia.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;

public interface WmNewsService  extends IService<WmNews>{

	/**
	 * 全てのニュースを
	 * @param dto
	 * @return
	 */
	public ResponseResult findAll(WmNewsPageReqDto dto);
	
	
}
