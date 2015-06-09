package com.zl.webService.service.impl;

import com.zl.webService.service.ServiceException;
import com.zl.webService.dao.StudentDAO;
import com.zl.webService.entity.Student;
import com.zl.webService.service.StudentService;
import com.zl.webService.Constants;
import com.zl.webService.util.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Iterator;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:30:27
 */
public class StudentServiceImpl implements StudentService {
    public static final Log LOG = LogFactory.getLog(StudentServiceImpl.class);
    private StudentDAO studentDAO;

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student createStudent(Student student) {
        try {
            student = getStudentDAO().create(student);
        } catch (Exception e) {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.CREATE_STUDENT_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.CREATE_STUDENT_ERROR));
        }
        return student;
    }

    public void deleteStudent(Student student) {
        try {
            getStudentDAO().deleteStudent(student);
        } catch (Exception e) {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.DELETE_STUDENT_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.DELETE_STUDENT_ERROR));
        }
    }

    public List<Student> searchStudent(Student student, int pageNumber, int pageSize) {
        List studentList = null;
        try {
            studentList = getStudentDAO().searchStudent(student, pageNumber, pageSize);
        } catch (Exception e) {
            LOG.error("查询学生信息失败：" + student);
        }
        return studentList;
    }

    public void modifyStudent(Student student) {
        try {
            getStudentDAO().modifyStudent(student);
        } catch (Exception e) {
            LOG.error(Configuration.getInstance().getValue(
                    Constants.MODIFY_STUDENT_ERROR));
            throw new ServiceException(
                    Configuration.getInstance().getValue(
                            Constants.MODIFY_STUDENT_ERROR));
        }
    }

    public void timeAction(){
        LOG.debug("Hello World!");
    }
}
