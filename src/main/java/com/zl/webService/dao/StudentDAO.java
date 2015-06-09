package com.zl.webService.dao;

import com.zl.webService.entity.Student;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:29:32
 */
public interface StudentDAO {
    /**
     * 创建学生信息
     *
     * @param student
     * @return
     */
    Student create(Student student);

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
