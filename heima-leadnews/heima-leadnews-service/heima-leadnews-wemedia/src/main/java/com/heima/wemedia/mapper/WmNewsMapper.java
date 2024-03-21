package com.heima.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.wemedia.pojos.WmChannel;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNews;

import org.apache.ibatis.annotations.Mapper;




@Mapper
public interface WmNewsMapper extends BaseMapper<WmNews> {
}