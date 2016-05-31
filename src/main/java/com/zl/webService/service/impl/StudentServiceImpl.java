package com.zl.webService.service.impl;

import com.zl.webService.annotation.CustomDataSource;
import com.zl.webService.service.BaseService;
import com.zl.webService.service.ServiceException;
import com.zl.webService.dao.StudentDAO;
import com.zl.webService.entity.Student;
import com.zl.webService.service.StudentService;
import com.zl.webService.Constants;
import com.zl.webService.util.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author <a href="mailto:luckylucky3210@163.com">ZL</a>
 * @version $Id$
 *          Date: 2013-12-8
 *          Time: 13:30:27
 */
public class StudentServiceImpl implements BaseService<Student>,StudentService {
    public static final Log LOG = LogFactory.getLog(StudentServiceImpl.class);
    private StudentDAO studentDAO;

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public Student create(Student student) {
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

    public void delete(Student student) {
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

    @CustomDataSource(key = "slave")
    public List<Student> search(Student student, int pageNumber, int pageSize) {
        List studentList = null;
        try {
            studentList = getStudentDAO().searchStudent(student, pageNumber, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("查询学生信息失败：" + student);
        }
        return studentList;
    }

    public void update(Student student) {
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

    public static void main(String[] args){
        ApplicationContext atx = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext-student.xml"});
        StudentService studentService = (StudentService) atx.getBean("studentService");
        Student student = new Student();
        System.out.println(studentService.search(student,1,1));
    }
}
