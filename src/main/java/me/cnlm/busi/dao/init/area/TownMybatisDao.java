package me.cnlm.busi.dao.init.area;


import me.cnlm.busi.entity.init.area.Town;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface TownMybatisDao {
    List<Town> findTownsByDistrictCode(String districtCode);
}
