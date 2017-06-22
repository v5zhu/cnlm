package net.leebao.open.busi.dao;

import net.leebao.open.busi.params.PageParam;
import net.leebao.open.busi.entity.Student;

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
