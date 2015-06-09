package com.zl.webService.dao.impl;

import com.zl.webService.dao.StudentDAO;
import com.zl.webService.entity.Student;
import com.zl.webService.dao.impl.BaseDAO;

import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:37:49
 */
public class StudentDAOImpl extends BaseDAO implements StudentDAO {
    public Student create(Student student) {
        getSqlMapClientTemplate().insert(
                "student.create", student);
        return student;
    }

    public void deleteStudent(Student student) {
        getSqlMapClientTemplate().delete(
                "student.delete", student);
    }

    public List<Student> searchStudent(Student student, int pageNumber, int pageSize) {
        return getSqlMapClientTemplate().queryForList(
                "student.searchStudent", student, pageNumber, pageSize);
    }

    public void modifyStudent(Student student) {
        getSqlMapClientTemplate().update(
                "student.modifyStudent", student);
    }
}
