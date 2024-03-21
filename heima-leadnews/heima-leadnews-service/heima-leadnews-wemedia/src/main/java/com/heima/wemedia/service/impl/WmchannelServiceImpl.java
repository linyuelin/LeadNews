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
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.utils.thread.WmThreadLocalUtil;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmchannelMapper;
import com.heima.wemedia.service.WmMaterialService;
import com.heima.wemedia.service.WmchannelService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class WmchannelServiceImpl extends ServiceImpl<WmchannelMapper,WmChannel> implements WmchannelService {
	
	
	/**
	 * 全てのチャンネルをクエリ
	 */
	@Override
	public ResponseResult findAll() {
		
		return ResponseResult.okResult(list());
	}
	
	
	
}
