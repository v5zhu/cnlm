package com.vvboot.end.busi.dao.init.area;


import com.vvboot.end.busi.entity.init.area.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface CityMybatisDao {
    List<City> findCitiesByProvinceCode(String provinceCode);
}
