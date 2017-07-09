package net.leebao.busi.service;

import com.alibaba.fastjson.JSONArray;
import net.leebao.busi.entity.Student;
import net.leebao.busi.params.PageParam;
import net.leebao.core.commons.Pageable;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface StudentService {
    void addStudent(Student student);

    JSONArray dropdown(Long orgId);

    Pageable studentList(PageParam pageParam);
}
