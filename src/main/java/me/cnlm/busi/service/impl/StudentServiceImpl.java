package me.cnlm.busi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.cnlm.busi.entity.Student;
import me.cnlm.busi.service.StudentService;
import me.cnlm.core.commons.PageParam;
import me.cnlm.core.commons.Pageable;
import me.cnlm.utils.DateUtils;
import me.cnlm.busi.dao.StudentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/20
 * @description:
 */
@SuppressWarnings("ALL")
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    @Transactional
    public void addStudent(Student student) {
        student.setCreateTime(DateUtils.nowTime());
        studentDao.addStudent(student);
    }

    @Override
    public JSONArray dropdown(Long orgId) {
        List<Student> list = studentDao.dropdown(orgId);
        JSONArray array = JSONArray.parseArray(JSONArray.toJSONString(list));
        return array;
    }

    @Override
    public Pageable studentList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize(), true);
        List<Student> list = studentDao.pageList(pageParam);
        PageInfo pageInfo = new PageInfo(list);

        Pageable pageable = BeanMapper.map(pageInfo, Pageable.class);
        pageable.setList(list);

        return pageable;
    }
}
