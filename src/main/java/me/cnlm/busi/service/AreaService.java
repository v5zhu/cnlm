package me.cnlm.busi.service;


import me.cnlm.busi.dto.init.area.*;

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
