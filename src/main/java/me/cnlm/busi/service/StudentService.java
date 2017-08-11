package me.cnlm.busi.service;

import com.alibaba.fastjson.JSONArray;
import me.cnlm.core.commons.PageParam;
import me.cnlm.core.commons.Pageable;
import me.cnlm.busi.entity.Student;

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
