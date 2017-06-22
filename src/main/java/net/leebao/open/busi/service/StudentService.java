package net.leebao.open.busi.service;

import com.alibaba.fastjson.JSONArray;
import net.leebao.open.busi.entity.Student;
import net.leebao.open.busi.params.PageParam;
import net.leebao.open.core.commons.Pageable;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface StudentService {
    void addStudent(Student student);

    JSONArray dropdown();

    Pageable studentList(PageParam pageParam);
}
