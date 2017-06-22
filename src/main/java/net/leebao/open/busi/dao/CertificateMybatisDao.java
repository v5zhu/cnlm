package net.leebao.open.busi.dao;


import net.leebao.open.busi.entity.Certificate;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by LONG on 2017/2/22.
 */
@Mapper
public interface CertificateMybatisDao {

    int addCert(Certificate cert);
}
