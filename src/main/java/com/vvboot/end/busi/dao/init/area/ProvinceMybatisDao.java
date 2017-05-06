package com.vvboot.end.busi.dao.init.area;


import com.vvboot.end.busi.entity.init.area.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface ProvinceMybatisDao {
    List<Province> findAllProvinces();
}
