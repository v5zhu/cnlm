package com.vvboot.end.busi.controller;

import com.vvboot.end.busi.dto.init.area.*;
import com.vvboot.end.busi.service.RegionService;
import com.vvboot.end.core.commons.Success;
import com.vvboot.end.core.exception.LeeBaoException;
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
 * Created by LONG on 2017/4/9.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v1/region")
public class RegionController {
    private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/provinces", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity provinceList() {
        try {
            logger.info("查看省份列表");
            List<ProvinceDto> provinces = regionService.findProvinces();
            Success ok = new Success(provinces, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/province/{provinceCode}/cities", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity cityList(@PathVariable("provinceCode") String provinceCode) {
        try {
            logger.info("查看province:[{}]的city列表", provinceCode);
            List<CityDto> cities = regionService.findCities(provinceCode);
            Success ok = new Success(cities, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/city/{cityCode}/districts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity districtList(@PathVariable("cityCode") String cityCode) {
        try {
            logger.info("查看city:[{}]的district列表", cityCode);
            List<DistrictDto> districts = regionService.findDistricts(cityCode);
            Success ok = new Success(districts, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/district/{districtCode}/towns", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity townList(@PathVariable("districtCode") String districtCode) {
        try {
            logger.info("查看district:[{}]的town列表", districtCode);
            List<TownDto> towns = regionService.findTowns(districtCode);
            Success ok = new Success(towns, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/town/{townCode}/villages", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity villageList(@PathVariable("townCode") String townCode) {
        try {
            logger.info("查看town:[{}]的village列表", townCode);
            List<VillageDto> villages = regionService.findVillages(townCode);
            Success ok = new Success(villages, "查询成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        } catch (LeeBaoException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
