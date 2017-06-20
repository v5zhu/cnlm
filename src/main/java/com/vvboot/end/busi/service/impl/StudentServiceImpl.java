package com.vvboot.end.busi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vvboot.end.busi.dao.SchoolOrganizationDao;
import com.vvboot.end.busi.dao.StudentDao;
import com.vvboot.end.busi.entity.SchoolOrganization;
import com.vvboot.end.busi.entity.Student;
import com.vvboot.end.busi.params.PageParam;
import com.vvboot.end.busi.service.SchoolOrganizationService;
import com.vvboot.end.busi.service.StudentService;
import com.vvboot.end.core.commons.Pageable;
import com.vvboot.end.utils.DateUtils;
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
    public JSONArray dropdown() {
        List<Student> list = studentDao.dropdown();
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
