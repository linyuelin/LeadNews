package com.heima.wemedia.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;

public interface WmMaterialService  extends IService<WmMaterial>{

	
	
	
	/**
	 * イメージのアップロード
	 * @param multipartFile
	 * @return
	 */
	public ResponseResult uploadPicture(MultipartFile multipartFile) ;
	
	
	/**
	 * 素材のリストをクエリ
	 * @param dto
	 * @return
	 */
	public ResponseResult findList(WmMaterialDto dto);
}
