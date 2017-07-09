package net.leebao.busi.dao.common;

import net.leebao.busi.entity.common.Approval;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by PAX on 2017/4/13.
 */
@Mapper
public interface ApprovalMybatisDao {
    int addApproval(Approval approval);
}
