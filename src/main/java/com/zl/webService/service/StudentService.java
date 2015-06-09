package com.zl.webService.service;

import com.zl.webService.entity.Student;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2014-1-9
 *          Time: 21:38:12
 */
public interface StudentService {
    /**
     * 创建学生信息
     *
     * @param student
     * @return
     */
    Student createStudent(Student student);

    /**
     * 删除学生信息
     *
     * @param student
     */
    void deleteStudent(Student student);

    /**
     * 查询学生信息
     *
     * @param student
     * @return
     */
    List searchStudent(Student student, int pageNumber, int pageSize);

    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    void modifyStudent(Student student);

}
