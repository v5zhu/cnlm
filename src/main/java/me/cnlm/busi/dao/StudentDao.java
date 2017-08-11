package me.cnlm.busi.dao;

import me.cnlm.busi.entity.Student;
import me.cnlm.core.commons.PageParam;

import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface StudentDao {
    void addStudent(Student student);

    List<Student> dropdown(Long orgId);

    List<Student> pageList(PageParam pageParam);
}
