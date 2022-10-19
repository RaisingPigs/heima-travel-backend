package com.pan.es.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pan.es.common.result.BaseResponse;
import com.pan.es.common.result.ResultVo;
import com.pan.es.model.entity.Hotel;
import com.pan.es.model.request.PageRequest;
import com.pan.es.model.vo.HotelVo;
import com.pan.es.model.vo.PageVo;
import com.pan.es.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2022-10-08 11:44
 **/
@Api(tags = "酒店接口")
@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelController {
    @Resource
    private HotelService hotelService;

    @ApiOperation("酒店分页查询")
    @GetMapping("/page")
    public BaseResponse<PageVo<HotelVo>> search(@Validated PageRequest pageRequest) throws IOException {

        PageVo<HotelVo> hotelPage = hotelService.getHotelPage(pageRequest);

        return ResultVo.success(hotelPage);
    }
}
