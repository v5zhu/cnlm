package me.cnlm.busi.dao;


import me.cnlm.busi.entity.PhoneCode;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhuxl on 2015/5/20.
 */

@Mapper
public interface PhoneCodeMybatisDao {
    PhoneCode findByPhone(String phone);

    int insertPhoneCode(PhoneCode phoneCode);

    int updatePhoneCode(PhoneCode phoneCode);

}
