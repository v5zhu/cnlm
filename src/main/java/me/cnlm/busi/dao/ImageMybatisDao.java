package me.cnlm.busi.dao;

import me.cnlm.busi.entity.Image;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LONG on 2017/2/22.
 */
@Mapper
public interface ImageMybatisDao {
    int addImage(Image image);
}
