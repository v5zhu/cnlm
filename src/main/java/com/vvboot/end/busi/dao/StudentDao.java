package com.vvboot.end.busi.dao;

import com.vvboot.end.busi.entity.Student;
import com.vvboot.end.busi.params.PageParam;

import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
public interface StudentDao {
    void addStudent(Student student);

    List<Student> dropdown();

    List<Student> pageList(PageParam pageParam);
}
