package com.heima.wemedia.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper,WmMaterial> implements WmMaterialService {
	
	
	 
     @Autowired
     private FileStorageService fileStorageService;
	
	/**
	 * イメージのアップロード
	 * @param multipartFile
	 * @return
	 */
	
	public ResponseResult uploadPicture(MultipartFile multipartFile) {
	    //　パラメーターのチェック
		
	    if(multipartFile == null || multipartFile.getSize() == 0) {
	    	return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
	    }
	    //minioにアップロード
	    String fileName = UUID.randomUUID().toString().replace("-", "");
	    
	    String originalFilename = multipartFile.getOriginalFilename();
	    
	    String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
	   
	    String fileId = null;
	    
	    
		try {
				fileId = fileStorageService.uploadImgFile("",fileName+postfix,multipartFile.getInputStream());
			 
			log.info("イメージをminioにアップロードした,fileId :{}" , fileId);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("アップロード失敗");
		}
	    
	  
	   //url をデーターベースに
		WmMaterial wmMaterial = new WmMaterial();
		wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
		wmMaterial.setUrl(fileId);
		wmMaterial.setIsCollection((short)0);
		wmMaterial.setType((short)0);
		wmMaterial.setCreatedTime(new Date());
		save(wmMaterial);
		
	
	   //結果を返す
	
		return ResponseResult.okResult(wmMaterial);
	}
	
	
	/**
	 * 素材のリストをクエリ
	 * @param dto
	 * @return
	 */
	@Override
	public ResponseResult findList(WmMaterialDto dto) {
		// 1. パラメータのチェック
		dto.checkParam();
		
		// 2. ページング検索
		IPage page =new Page(dto.getPage(),dto.getSize());
		LambdaQueryWrapper<WmMaterial> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		// 気に入りか
		if(dto.getIsCollection() != null && dto.getIsCollection() == 1) {
			lambdaQueryWrapper.eq(WmMaterial::getIsCollection, dto.getIsCollection());
		}
		
		//ユーザーでクエリ
		lambdaQueryWrapper.eq(WmMaterial::getUserId, WmThreadLocalUtil.getUser().getId());
		
		//按时间倒叙查询
		lambdaQueryWrapper.orderByDesc(WmMaterial::getCreatedTime);
		page = page(page,lambdaQueryWrapper);
		// 3. 結果を返す
		
		ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
		responseResult.setData(page.getRecords());
		
		return responseResult;
		

	}
	
	
	
      
	
}
