package com.pan.es.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pan.es.model.entity.Hotel;
import com.pan.es.model.request.PageRequest;
import com.pan.es.model.vo.HotelVo;
import com.pan.es.model.vo.PageVo;

import java.io.IOException;

/**
* @author Mr.Pan
* @description 针对表【tb_hotel】的数据库操作Service
* @createDate 2022-10-08 11:41:42
*/
public interface HotelService extends IService<Hotel> {
    PageVo<HotelVo> getHotelPage(PageRequest pageRequest) throws IOException;
}
