package com.heima.wemedia.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dtos.WmNewsPageReqDto;
import com.heima.wemedia.service.WmNewsService;

@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {
	
	@Autowired
	private WmNewsService wmNewsService;
	
	/**
	 * 全てのニュースを
	 * @param dto
	 * @return
	 */
    
	@PostMapping("/list")
	public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto) {
		return wmNewsService.findAll(dto);
	}
}
