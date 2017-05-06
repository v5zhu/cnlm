package com.vvboot.end.busi.controller;

import com.vvboot.end.busi.dto.init.area.*;
import com.vvboot.end.busi.service.AreaService;
import com.vvboot.end.core.commons.Success;
import com.vvboot.end.core.exception.CoreException;
import com.vvboot.end.core.exception.InnerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/area")
public class AreaController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/provinces", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity provinces() {
        try {
            logger.info("查询省份");
            List<ProvinceDto> provinceDtos = areaService.findAllProvinces();
            Success ok = new Success(provinceDtos, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/province/{provinceCode}/cities", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity cities(@PathVariable("provinceCode") String provinceCode) {
        try {
            logger.info("查询城市");
            List<CityDto> cityDtos = areaService.findAllCities(provinceCode);
            Success ok = new Success(cityDtos, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/province/city/{cityCode}/districts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity districts(@PathVariable("cityCode") String cityCode) {
        try {
            logger.info("查询区县");
            List<DistrictDto> districtDtos = areaService.findAllDistricts(cityCode);
            Success ok = new Success(districtDtos, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/province/city/district/{districtCode}/towns", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity towns(@PathVariable("districtCode") String districtCode) {
        try {
            logger.info("查询城镇");
            List<TownDto> townDtos = areaService.findAllTowns(districtCode);
            Success ok = new Success(townDtos, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/province/city/district/town/{townCode}/villages", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity villages(@PathVariable("townCode") String townCode) {
        try {
            logger.info("查询乡村");
            List<VillageDto> villageDtos = areaService.findAllVillages(townCode);
            Success ok = new Success(villageDtos, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (CoreException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        } catch (InnerException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
