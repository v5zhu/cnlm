package net.leebao.busi.dao;

import net.leebao.busi.entity.Student;
import net.leebao.busi.params.PageParam;

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
