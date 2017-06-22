package net.leebao.open.busi.service;


import com.leebao.open.busi.dto.init.area.*;
import com.vvboot.end.busi.dto.init.area.*;
import net.leebao.open.busi.dto.init.area.*;

import java.util.List;

/**
 * Created by LONG on 2017/3/22.
 */
public interface AreaService {
    List<ProvinceDto> findAllProvinces();

    List<CityDto> findAllCities(String provinceCode);

    List<DistrictDto> findAllDistricts(String cityId);

    List<TownDto> findAllTowns(String districtCode);

    List<VillageDto> findAllVillages(String townId);
}
